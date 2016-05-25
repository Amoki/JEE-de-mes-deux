package model;


public class CommentModel {
    private int author;
    private int recipe;
    private String comment;

    public CommentModel() {}

    public CommentModel(int author, int recipe, String comment) {
        this.author = author;
        this.recipe = recipe;
        this.comment = comment;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
