package ilgulee.com.flickrokhttpjsonglide;

class Photo {
    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mLink;
    private String mSmallImageURL;
    private String mTags;
    private String mLargeImageURL;

    public Photo(String title, String author, String authorId, String link, String smallImageURL, String tags, String largeImageURL) {
        mTitle = title;
        mAuthor = author;
        mAuthorId = authorId;
        mLink=link;
        mSmallImageURL = smallImageURL;
        mTags = tags;
        mLargeImageURL = largeImageURL;
    }

    String getTitle() {
        return mTitle;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    String getAuthor() {
        return mAuthor;
    }

    void setAuthor(String author) {
        mAuthor = author;
    }

    String getAuthorId() {
        return mAuthorId;
    }

    void setAuthorId(String authorId) {
        mAuthorId = authorId;
    }

    String getSmallImageURL() {
        return mSmallImageURL;
    }

    void setSmallImageURL(String smallImageURL) {
        mSmallImageURL = smallImageURL;
    }

    String getTags() {
        return mTags;
    }

    void setTags(String tags) {
        mTags = tags;
    }

    String getLargeImageURL() {
        return mLargeImageURL;
    }

    void setLargeImageURL(String largeImageURL) {
        mLargeImageURL = largeImageURL;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mSmallImageURL='" + mSmallImageURL + '\'' +
                ", mTags='" + mTags + '\'' +
                ", mLargeImageURL='" + mLargeImageURL + '\'' +
                '}';
    }
}

