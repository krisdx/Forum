package com.forum.areas.question.service;

import com.forum.areas.question.entity.Category;
import com.forum.areas.question.entity.Comment;
import com.forum.areas.question.entity.Question;
import com.forum.areas.question.entity.Tag;
import com.forum.areas.question.exception.NoSuchCategoryException;
import com.forum.areas.question.exception.NoSuchQuestionException;
import com.forum.areas.question.exception.NoSuchTagException;
import com.forum.areas.question.model.bind.EditBindModel;
import com.forum.areas.question.model.bind.QuestionAddBindModel;
import com.forum.areas.question.model.view.CommentViewModel;
import com.forum.areas.question.model.view.QuestionViewMiniModel;
import com.forum.areas.question.model.view.QuestionViewModel;
import com.forum.areas.question.model.view.TagViewModel;
import com.forum.areas.question.repository.QuestionRepository;
import com.forum.areas.user.entity.User;
import com.forum.areas.user.service.UserService;
import com.forum.util.AuthenticationUtils;
import com.forum.util.Constants;
import com.forum.util.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository,
                               ModelMapper modelMapper,
                               CategoryService categoryService,
                               TagService tagService,
                               UserService userService) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public void add(QuestionAddBindModel questionAddBindModel) {
        Category category = this.categoryService.findByCategoryName(questionAddBindModel.getCategory());
        Set<Tag> tags = this.parseTags(questionAddBindModel.getTags());
        Question question = this.modelMapper.map(questionAddBindModel, Question.class);
        question.setDateTime(LocalDateTime.now());
        question.setTags(tags);
        question.setCategory(category);
        question = this.questionRepository.save(question);

        User currentUser = this.userService.addQuestionToCurrentUser(question);
        question.setUser(currentUser);
        this.questionRepository.save(question);
    }

    @Override
    public List<QuestionViewMiniModel> findAllWithoutCommentsAndTags() {
        List<Question> questions = this.questionRepository.findAll();
        questions = questions.stream()
                .sorted((a, b) -> b.getId().compareTo(a.getId()))
                .collect(Collectors.toList());
        return this.convert(questions);
    }

    @Override
    public QuestionViewModel findById(Long id) {
        Question question = this.questionRepository.findOne(id);
        if (question == null) {
            throw new NoSuchQuestionException();
        }

        QuestionViewModel questionViewModel = this.convert(question);

        List<CommentViewModel> commentSortedByDateDesc = questionViewModel.getComments()
                .stream()
                .sorted(Comparator.comparing(CommentViewModel::getId))
                .collect(Collectors.toList());
        questionViewModel.setComments(commentSortedByDateDesc);

        List<TagViewModel> tagViewModels = questionViewModel.getTags()
                .stream()
                .sorted(Comparator.comparing(TagViewModel::getId))
                .collect(Collectors.toList());
        questionViewModel.setTags(tagViewModels);

        for (CommentViewModel commentViewModel : questionViewModel.getComments()) {
            LocalDateTime dateTime = LocalDateTime.parse(commentViewModel.getDate());
            commentViewModel.setDate(DateUtils.parseDateTime(dateTime));
        }

        return questionViewModel;
    }

    @Override
    public void addComment(Long questionId, Comment comment) {
        Question question = this.questionRepository.findOne(questionId);
        question.getComments().add(comment);
        this.questionRepository.save(question);
    }

    @Override
    public QuestionViewModel edit(Long questionId, EditBindModel editBindModel) {
        Question question = this.questionRepository.findOne(questionId);
        if (question.getQuestion().equals(editBindModel.getEditedText())) {
            return this.convert(question);
        }

        question.setDateTime(LocalDateTime.now());
        question.setQuestion(editBindModel.getEditedText());
        question.setHasBeenEdited(true);
        this.questionRepository.save(question);
        return this.convert(question);
    }

    @Override
    public void delete(Long questionId) {
        this.questionRepository.deleteRelationWithUser(questionId);
        this.questionRepository.delete(questionId);
    }

    @Override
    public List<QuestionViewMiniModel> findQuestionsByTag(String tagName) {
        Tag tag = this.tagService.findByTagName(tagName);
        if (tag == null) {
            throw new NoSuchTagException();
        }

        List<Question> questions = this.questionRepository.findQuestionsByTag(tag.getId());
        return this.convert(questions);
    }

    @Override
    public List<QuestionViewMiniModel> findQuestionsByCategory(String categoryName) {
        Category category = this.categoryService.findByCategoryName(categoryName);
        if (category == null) {
            throw new NoSuchCategoryException();
        }

        List<Question> questions = this.questionRepository.findQuestionsByCategory(category.getId());
        return this.convert(questions);
    }

    private QuestionViewModel convert(Question question) {
        QuestionViewModel questionViewModel = this.modelMapper.map(question, QuestionViewModel.class);
        questionViewModel.setDate(DateUtils.parseDateTime(question.getDateTime()));
        questionViewModel.setUsername(question.getUser().getUsername());

        if (AuthenticationUtils.getCurrentUserUsername() != null &&
                !AuthenticationUtils.getCurrentUserUsername().equals(Constants.ANONYMOUS_USER)) {
            Long currentUserId = this.userService.findByUsername(AuthenticationUtils.getCurrentUserUsername()).getId();
            questionViewModel.setIsLiked(question.getUserLikes().get(currentUserId));
            this.setCommentsLikes(question, questionViewModel, currentUserId);
        }

        return questionViewModel;
    }

    private void setCommentsLikes(Question question, QuestionViewModel questionViewModel, Long currentUserId) {
        int index = 0;
        for (Comment comment : question.getComments()) {
            CommentViewModel c = questionViewModel.getComments().get(index);
            c.setIsLiked(comment.getUserLikes().get(currentUserId));
            index++;
        }
    }

    private List<QuestionViewMiniModel> convert(List<Question> questions) {
        List<QuestionViewMiniModel> questionViewMiniModels = new ArrayList<>();
        for (Question question : questions) {
            QuestionViewMiniModel questionViewMiniModel = this.convert(question);
            questionViewMiniModels.add(questionViewMiniModel);
        }

        return questionViewMiniModels;
    }

    private Set<Tag> parseTags(String inputTagNames) {
        Set<Tag> tags = new HashSet<>();
        String[] tagNames = inputTagNames.split(",");
        for (String tagName : tagNames) {
            Tag tag = this.tagService.createTagIfNotExists(tagName.trim());
            tags.add(tag);
        }

        return tags;
    }
}