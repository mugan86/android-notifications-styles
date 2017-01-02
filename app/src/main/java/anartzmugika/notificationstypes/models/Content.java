package anartzmugika.notificationstypes.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;

/**************************************************************************************************
 * Created by anartzmugika on 13/12/16.
 *************************************************************************************************/

public class Content implements Parcelable {

    private long id;
    private String image;
    private String date;
    private String body;
    private String source;
    private String author;
    private String author_img;

    public Content(long id, String image, String date, String body, String source, String author, String author_img)
    {
        setId(id);
        setImage(image);
        setDate(date);
        setBody(body);
        setSource(source);
        setAuthor(author);
        setAuthor_img(author_img);
    }

    public Content(Parcel in) {
        readParcel(in);
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor_img() {
        return author_img;
    }

    public void setAuthor_img(String author_img) {
        this.author_img = author_img;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public Spanned getMessageContent() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) return Html.fromHtml(getBody(),0);
        return Html.fromHtml(getBody());
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readParcel(Parcel in)
    {
        id = in.readLong();
        image = in.readString();
        date = in.readString();
        body = in.readString();
        source = in.readString();
        author = in.readString();
        author_img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(image);
        parcel.writeString(date);
        parcel.writeString(body);
        parcel.writeString(source);
        parcel.writeString(author);
        parcel.writeString(author_img);
    }
}
