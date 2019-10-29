package com.internousdev.arizona.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.arizona.dto.UserInfoDTO;
import com.internousdev.arizona.util.DBConnector;

public class UserInfoDAO {

	public int createUser(String userId, String password, String familyName, String firstName,
			String familyNameKana, String firstNameKana, String sex, String email) throws SQLException{

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		int count = 0;

		String sql = "INSERT INTO user_info(user_id, password, family_name, first_name, family_name_kana, first_name_kana, "
				+ "sex, email, status, logined, regist_date, update_date) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, familyName);
			preparedStatement.setString(4, firstName);
			preparedStatement.setString(5, familyNameKana);
			preparedStatement.setString(6, firstNameKana);
			preparedStatement.setString(7, sex);
			preparedStatement.setString(8, email);
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, 1);

			count = preparedStatement.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			connection.close();
			}
		return count;
	}

	public boolean isExistsUserInfo(String userId, String password) throws SQLException{

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		boolean result = false;

		String sql = "SELECT count(*) AS cnt FROM user_info WHERE BINARY user_id=? AND BINARY password=?";
		//sqlに入っているidとpassが合致した数のことをcount
		//asでcountをcntという名前に変えている
		//BINARYは大文字と小文字を区別する

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				if(resultSet.getInt("cnt") > 0){//合致した数が0より多かったらtrue
					result = true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
				connection.close();
		}
		return result;
	}

	public boolean isExistsUserInfo(String userId) throws SQLException{

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		boolean result = false;

		String sql = "SELECT count(*) AS cnt FROM user_info WHERE BINARY user_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				if(resultSet.getInt("cnt") > 0){
					result = true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
				connection.close();
		}
		return result;
	}

	public UserInfoDTO getUserInfo(String userId, String password) throws SQLException{

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();

		String sql = "SELECT * FROM user_info WHERE user_id=? AND password=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				userInfoDTO.setId(resultSet.getInt("id"));
				userInfoDTO.setUserId(resultSet.getString("user_id"));
				userInfoDTO.setPassword(resultSet.getString("password"));
				userInfoDTO.setFamilyName(resultSet.getString("family_name"));
				userInfoDTO.setFirstName(resultSet.getString("first_name"));
				userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
				userInfoDTO.setSex(resultSet.getInt("sex"));
				userInfoDTO.setEmail(resultSet.getString("email"));
				userInfoDTO.setLogined(resultSet.getInt("status"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
				connection.close();
		}
		return userInfoDTO;
	}

	public UserInfoDTO getUserInfo(String userId) throws SQLException {

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();

		String sql = "SELECT * FROM user_info WHERE user_id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				userInfoDTO.setId(resultSet.getInt("id"));
				userInfoDTO.setUserId(resultSet.getString("user_id"));
				userInfoDTO.setPassword(resultSet.getString("password"));
				userInfoDTO.setFamilyName(resultSet.getString("family_name"));
				userInfoDTO.setFirstName(resultSet.getString("first_name"));
				userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
				userInfoDTO.setFirstNameKana(resultSet.getString("first_name_kana"));
				userInfoDTO.setSex(resultSet.getInt("sex"));
				userInfoDTO.setEmail(resultSet.getString("email"));
				userInfoDTO.setLogined(resultSet.getInt("logined"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				connection.close();
		}
		return userInfoDTO;
	}

	public int resetPassword(String userId, String password) throws SQLException {

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "UPDATE user_info SET password=?, update_date=now() where user_id=?";

		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, userId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				connection.close();
		}
		return result;
	}

}

