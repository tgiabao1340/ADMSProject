package application;

/**
 * 
 * @category Xuất các thông báo khi cảnh báo
 *
 */
public class ErrorAlert {
	private String message;
	private String detail;

	public ErrorAlert(String message, String detail) {
		super();
		this.message = message;
		this.detail = detail;
	}

	public ErrorAlert() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
