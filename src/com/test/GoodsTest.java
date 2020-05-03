/**
 * 
 */
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.test.commons.CommonUtils;
import com.test.jdbc.utils.JDBCUtils;

/**
 * TODO
 * @author Prince
 * @date 2020年4月30日  
 */
public class GoodsTest {
	@Test
	public void fun() throws SQLException {
		Connection con = JDBCUtils.getConnection();
		String sql = "insert into goods values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		for(int i = 0; i < 300; i ++) {
			pstmt.setString(1, CommonUtils.uuid());
			pstmt.setString(2, "test_" + i);
			pstmt.setInt(3, i * 10 + 365);
			pstmt.setInt(4, i * 5 + 12);
			pstmt.setInt(5, i * 10 + 13);
			pstmt.setString(6, "test_备注");
			pstmt.setString(7, i % 2 == 0 ? "first" : "second");
			
			pstmt.addBatch(); //添加批，这一组参数就保存在集合中了
		}
		pstmt.executeBatch(); //执行批
	}
}
