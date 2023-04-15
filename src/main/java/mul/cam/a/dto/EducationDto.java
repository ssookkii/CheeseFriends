package mul.cam.a.dto;

public class EducationDto {
	private String eduCode;
	private String eduName;
	private String eduAddress;
	private String eduPhone;
	private String eduId;
	
	public EducationDto() {
		
	}

	public EducationDto(String eduCode, String eduName, String eduAddress, String eduPhone) {
		super();
		this.eduCode = eduCode;
		this.eduName = eduName;
		this.eduAddress = eduAddress;
		this.eduPhone = eduPhone;
	}
	

	public EducationDto(String eduCode, String eduName, String eduAddress, String eduPhone, String eduId) {
		super();
		this.eduCode = eduCode;
		this.eduName = eduName;
		this.eduAddress = eduAddress;
		this.eduPhone = eduPhone;
		this.eduId = eduId;
	}

	public String getEduCode() {
		return eduCode;
	}

	public void setEduCode(String eduCode) {
		this.eduCode = eduCode;
	}

	public String getEduName() {
		return eduName;
	}

	public void setEduName(String eduName) {
		this.eduName = eduName;
	}

	public String getEduAddress() {
		return eduAddress;
	}

	public void setEduAddress(String eduAddress) {
		this.eduAddress = eduAddress;
	}

	public String getEduPhone() {
		return eduPhone;
	}

	public void setEduPhone(String eduPhone) {
		this.eduPhone = eduPhone;
	}

	public String getEduId() {
		return eduId;
	}

	public void setEduId(String eduId) {
		this.eduId = eduId;
	}
	
	
}
