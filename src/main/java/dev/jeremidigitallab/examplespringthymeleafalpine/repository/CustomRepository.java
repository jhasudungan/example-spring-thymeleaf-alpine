package dev.jeremidigitallab.examplespringthymeleafalpine.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.jeremidigitallab.examplespringthymeleafalpine.entity.Category;
import dev.jeremidigitallab.examplespringthymeleafalpine.entity.Product;

@Component
public class CustomRepository {

	@Autowired
	private DataSource dataSource;

	public List<Category> searchCategoryByName(String categoryName) throws SQLException {

		List<Category> data = new ArrayList<Category>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			categoryName = "%" + categoryName.toLowerCase().trim() + "%";
			String sql = "SELECT * FROM CATEGORY WHERE TRIM(LOWER(CATEGORY_NAME)) LIKE ?";
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryName);
			rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();

				category.setCategoryId(rs.getString("CATEGORY_ID"));
				category.setCreatedDate(rs.getDate("CREATED_DATE"));
				category.setLastModified(rs.getDate("LAST_MODIFIED"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));

				data.add(category);
			}

		} finally {
			closePreparedConnection(con, ps, rs);
		}

		return data;
	}

	public List<Product> searchProductByName(String productName) throws SQLException {

		List<Product> data = new ArrayList<Product>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			productName = "%" + productName.toLowerCase().trim() + "%";
			String sql = "SELECT * FROM PRODUCT WHERE TRIM(LOWER(PRODUCT_NAME)) LIKE ?";
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, productName);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();

				product.setProductId(rs.getString("PRODUCT_ID"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));
				product.setCategoryId(rs.getString("CATEGORY_ID"));
				product.setCreatedDate(rs.getDate("CREATED_DATE"));
				product.setLastModified(rs.getDate("LAST_MODIFIED"));

				data.add(product);
			}

		} finally {
			closePreparedConnection(con, ps, rs);
		}

		return data;
	}

	private void closePreparedConnection(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (ps != null) {
			ps.close();
		}

		if (con != null) {
			con.close();
		}

	}
}
