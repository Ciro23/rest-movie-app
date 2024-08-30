package it.tino.postgres;

/**
 * Represents "Problem Details for HTTP APIs".
 * <a href="https://www.rfc-editor.org/rfc/rfc9457">RFC9457</a>
 */
public class ErrorResponse {

    private String type;
    private String title;
    private String detail;
    private String instance;

    public String getType() {
        return type;
    }

    public ErrorResponse setType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ErrorResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public ErrorResponse setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getInstance() {
        return instance;
    }

    public ErrorResponse setInstance(String instance) {
        this.instance = instance;
        return this;
    }
}
