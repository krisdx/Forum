package com.forum.areas.question.repository;

import com.forum.areas.question.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class TagRepositoryTest {

    private static final String TEST_TAG_NAME = "test";

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void findExistingTag_shouldReturnTag() throws Exception {
        // Arrange
        Tag expectedTag = new Tag();
        expectedTag.setTag(TEST_TAG_NAME);

        // Act
        this.tagRepository.save(expectedTag);
        Tag actualTag = this.tagRepository.findByTag(TEST_TAG_NAME);

        // Assert
        assertEquals(expectedTag, actualTag);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void findNonExistingTag_shouldReturnNull() throws Exception {
        // Act
        Tag actualTag = this.tagRepository.findByTag(TEST_TAG_NAME);

        // Assert
        assertNull(actualTag);
    }
}