package com.internousdev.arizona.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.arizona.dao.UserInfoDAO;
import com.internousdev.arizona.util.CommonUtility;
import com.internousdev.arizona.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware{

	private String userId;
	private String password;
	private String newPassword;
	private String reNewPassword;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<String> newPasswordErrorMessageList;
	private List<String> reNewPasswordErrorMessageList;
	private String passwordDatabaseErrorMessage;
	private String newPasswordDatabaseErrorMessage;
	private String concealedPassword;
	private Map<String, Object> session;

	public String execute(){

		String result = ERROR;

		InputChecker inputChecker = new InputChecker();

		session.put("userIdResetPassword", userId);

		userIdErrorMessageList = inputChecker.doCheck("ユーザーID",userId, 1, 8, true, false, false, true, false, false );
		passwordErrorMessageList = inputChecker.doCheck("現在のパスワード",password, 1, 16, true, false, false, true, false, false );
		newPasswordErrorMessageList = inputChecker.doCheck("新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false);
		reNewPasswordErrorMessageList = inputChecker.doCheck("新しいパスワード(再確認)", reNewPassword, 1, 16, true, false, false, true, false, false);
		//sizeでListの中身の数をチェックしている
		if(userIdErrorMessageList.size() > 0
		|| passwordErrorMessageList.size() > 0
		  || newPasswordErrorMessageList.size() > 0
		    || reNewPasswordErrorMessageList.size() > 0) {
			return result;
		}

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		try {
			if(!userInfoDAO.isExistsUserInfo(userId, password)){//UserInfoDAOで行われた処理がtrueなら下の処理を実行しない
				passwordDatabaseErrorMessage = "ユーザIDと現在のパスワードが異なります。";
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		newPasswordDatabaseErrorMessage = inputChecker.doPasswordCheck(newPassword, reNewPassword);

		if(newPasswordDatabaseErrorMessage == null){
			CommonUtility commonUtility = new CommonUtility();
			concealedPassword = commonUtility.concealPassword(newPassword);
			session.put("newPassword", newPassword);
			result = SUCCESS;
		}
		return result;
	}

	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getNewPassword(){
		return newPassword;
	}
	public void setNewPassword(String newPassword){
		this.newPassword = newPassword;
	}

	public String getReNewPassword(){
		return reNewPassword;
	}
	public void setReNewPassword(String reNewPassword){
		this.reNewPassword = reNewPassword;
	}

	public List<String> getUserIdErrorMessageList(){
		return userIdErrorMessageList;
	}
	public void setUserIdErrorMessageList(List<String> userIdErrorMessageList){
		this.userIdErrorMessageList = userIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList(){
		return passwordErrorMessageList;
	}
	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public List<String> getNewPasswordErrorMessageList() {
		return newPasswordErrorMessageList;
	}

	public void setNewPasswordErrorMessageList(List<String> newPasswordErrorMessageList) {
		this.newPasswordErrorMessageList = newPasswordErrorMessageList;
	}

	public String getPasswordDatabaseErrorMessage(){
		return passwordDatabaseErrorMessage;
	}
	public void setPasswordDatabaseErrorMessage(String passwordDatabaseErrorMessage){
		this.passwordDatabaseErrorMessage = passwordDatabaseErrorMessage;
	}

	public String getNewPasswordDatabaseErrorMessage(){
		return newPasswordDatabaseErrorMessage;
	}
	public void setNewPasswordDatabaseErrorMessage(String newPasswordDatabaseErrorMessage){
		this.newPasswordDatabaseErrorMessage = newPasswordDatabaseErrorMessage;
	}

	public List<String> getReNewPasswordErrorMessageList() {
		return reNewPasswordErrorMessageList;
	}
	public void setReNewPasswordErrorMessageList(List<String> reNewPasswordErrorMessageList) {
		this.reNewPasswordErrorMessageList = reNewPasswordErrorMessageList;
	}

	public String getConcealedPassword(){
		return concealedPassword;
	}
	public void setConcealedPassword(String concealedPassword) {
		this.concealedPassword = concealedPassword;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
