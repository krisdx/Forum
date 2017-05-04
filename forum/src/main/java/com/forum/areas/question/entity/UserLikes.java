package com.forum.areas.question.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

@Entity
@Table(name = "user_likes")
@SuppressWarnings("Duplicates")
public class UserLikes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BLOB")
    private HashMap<Long, Boolean> likes;

    @Column(name = "total_likes_count")
    private int totalLikesCount;

    @Column(name = "total_dislikes_count")
    private int totalDislikesCount;

    public UserLikes() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected HashMap<Long, Boolean> getLikes() {
        if (this.likes == null) {
            this.setLikes(new HashMap<>());
        }

        return this.likes;
    }

    protected void setLikes(HashMap<Long, Boolean> likes) {
        this.likes = likes;
    }

    public Boolean like(Long userId) {
        Boolean hasLiked = this.getLikes().get(userId);
        if (hasLiked == null) {
            /* Case 1 - The user has not liked or disliked this.
             We simply mark it as liked. */
            this.getLikes().put(userId, true);
            this.setTotalLikesCount(this.getTotalLikesCount() + 1);
            return true;
        } else if (hasLiked) {
            /* Case 2 - The user already likes this.
             We undo the like, by putting null as a value. */
            this.getLikes().put(userId, null);
            this.setTotalLikesCount(this.getTotalLikesCount() - 1);
            return null;
        } else {
            /* Case 3 - The user dislikes this, and now decides to like it.
             We mark it as liked, but return false, so the service can
             know it has to decrease the dislikes count. */
            this.getLikes().put(userId, true);
            this.setTotalLikesCount(this.getTotalLikesCount() + 1);
            this.setTotalDislikesCount(this.getTotalDislikesCount() - 1);
            return false;
        }
    }

    public Boolean dislike(Long userId) {
        Boolean hasLiked = this.getLikes().get(userId);
        if (hasLiked == null) {
            /* Case 1 - The user has not liked or disliked this.
             We simply mark it as disliked. */
            this.getLikes().put(userId, false);
            this.setTotalDislikesCount(this.getTotalDislikesCount() + 1);
            return true;
        } else if (!hasLiked) {
            /* Case 2 - The user already dislikes this.
             We undo the like, by putting null as a value. */
            this.getLikes().put(userId, null);
            this.setTotalDislikesCount(this.getTotalDislikesCount() - 1);
            return null;
        } else {
            /* Case 3 - The user likes this, and now decides to dislike it.
             We mark it as disliked, but return false, so the service can
             know it has to decrease the likes count. */
            this.getLikes().put(userId, false);
            this.setTotalDislikesCount(this.getTotalDislikesCount() + 1);
            this.setTotalLikesCount(this.getTotalLikesCount() - 1);
            return false;
        }
    }

    public Boolean get(Long userId) {
        return this.getLikes().get(userId);
    }

    public int getTotalLikesCount() {
        return this.totalLikesCount;
    }

    protected void setTotalLikesCount(int totalLikesCount) {
        this.totalLikesCount = totalLikesCount;
    }

    public int getTotalDislikesCount() {
        return this.totalDislikesCount;
    }

    protected  void setTotalDislikesCount(int totalDislikesCount) {
        this.totalDislikesCount = totalDislikesCount;
    }
}