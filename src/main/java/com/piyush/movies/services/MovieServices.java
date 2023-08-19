package com.piyush.movies.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.piyush.movies.entities.Movie;
import com.piyush.movies.entities.Review;

@Service
public class MovieServices {
	public ArrayList<Movie> generateMovieReport() {
		ArrayList<Movie> list = new ArrayList<>();
		Movie obj;

		Connection con;
		java.sql.Statement st;
		ResultSet rs;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

			st = con.createStatement();
			rs = st.executeQuery("select * from Movies;");

			while (rs.next()) {
				obj = new Movie();
			    obj.setMovieId(rs.getInt("movie_id"));
			    obj.setName(rs.getString("name"));
			    obj.setLanguage(rs.getString("language"));
			    obj.setCountry(rs.getString("country"));
			    obj.setCategory(rs.getString("category"));
			    obj.setReleaseYear(rs.getInt("release_year"));
			    obj.setCertificate(rs.getString("certificate"));
			    obj.setDirector(rs.getString("director"));
			    obj.setActors(rs.getString("actors"));
			    obj.setActresses(rs.getString("actresses"));
			    obj.setMusic(rs.getString("music"));
			    obj.setPlatform(rs.getString("platform"));
			    obj.setBudget(rs.getDouble("budget"));
			    obj.setCollection(rs.getDouble("collection"));
			    obj.setTrailerLink(rs.getString("trailer_link"));
			    obj.setThumbnailUrl(rs.getString("thumbnail_url"));
			    obj.setEmbeddedLink(rs.getString("embedded_link"));
			    

	           int movieId = rs.getInt("movie_id");
                ArrayList<Review> reviews = fetchReviewsForMovie(movieId);
                obj.setReviews(reviews);

	            list.add(obj);

			}


			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}
	
	private ArrayList<Review> fetchReviewsForMovie(int movieId) {
	    ArrayList<Review> reviews = new ArrayList<>(); // Use ArrayList<Review> instead of ArrayList<String>
	    
	    Connection con;
	    PreparedStatement pst;
	    ResultSet rs;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy");

	        String query = "SELECT username, review_text FROM Reviews WHERE movie_id = ?";
	        pst = con.prepareStatement(query);
	        pst.setInt(1, movieId);

	        rs = pst.executeQuery();
	        while (rs.next()) {
	            String username = rs.getString("username");
	            String reviewText = rs.getString("review_text");

	            Review review = new Review(username, reviewText);
	            reviews.add(review); // Add the review object to the ArrayList
	        }

	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return reviews;
	}

	
	
	
	 public ArrayList<Movie> filterMovies(String category, String platform) {
	        ArrayList<Movie> list = new ArrayList<>();
	        Movie obj;

	        Connection con;
	        PreparedStatement pst;
	        ResultSet rs;

	        try {
	    		Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
				con = DriverManager.getConnection(
						"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
		

	            String query = "SELECT * FROM Movies WHERE category LIKE ? AND platform LIKE ?";
	            pst = con.prepareStatement(query);
	            pst.setString(1, "%" + category + "%");
	            pst.setString(2, "%" + platform + "%");
	            rs = pst.executeQuery();

	    		while (rs.next()) {
					obj = new Movie();
				    obj.setMovieId(rs.getInt("movie_id"));
				    obj.setName(rs.getString("name"));
				    obj.setLanguage(rs.getString("language"));
				    obj.setCountry(rs.getString("country"));
				    obj.setCategory(rs.getString("category"));
				    obj.setReleaseYear(rs.getInt("release_year"));
				    obj.setCertificate(rs.getString("certificate"));
				    obj.setDirector(rs.getString("director"));
				    obj.setActors(rs.getString("actors"));
				    obj.setActresses(rs.getString("actresses"));
				    obj.setMusic(rs.getString("music"));
				    obj.setPlatform(rs.getString("platform"));
				    obj.setBudget(rs.getDouble("budget"));
				    obj.setCollection(rs.getDouble("collection"));
				    obj.setTrailerLink(rs.getString("trailer_link"));
				    obj.setThumbnailUrl(rs.getString("thumbnail_url"));
				    obj.setEmbeddedLink(rs.getString("embedded_link"));
				    list.add(obj);
				}

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        return list;
	    }
	 public ArrayList<Movie> searchMovies(String movieName) {
	        ArrayList<Movie> list = new ArrayList<>();
	        Movie obj;

	        Connection con;
	        PreparedStatement pst;
	        ResultSet rs;

	        try {
	    		Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
				con = DriverManager.getConnection(
						"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
		

	            String query = "SELECT * FROM Movies WHERE name = ?";
	            pst = con.prepareStatement(query);
	            pst.setString(1, movieName);
	            
	            rs = pst.executeQuery();

	    		while (rs.next()) {
					obj = new Movie();
				    obj.setMovieId(rs.getInt("movie_id"));
				    obj.setName(rs.getString("name"));
				    obj.setLanguage(rs.getString("language"));
				    obj.setCountry(rs.getString("country"));
				    obj.setCategory(rs.getString("category"));
				    obj.setReleaseYear(rs.getInt("release_year"));
				    obj.setCertificate(rs.getString("certificate"));
				    obj.setDirector(rs.getString("director"));
				    obj.setActors(rs.getString("actors"));
				    obj.setActresses(rs.getString("actresses"));
				    obj.setMusic(rs.getString("music"));
				    obj.setPlatform(rs.getString("platform"));
				    obj.setBudget(rs.getDouble("budget"));
				    obj.setCollection(rs.getDouble("collection"));
				    obj.setTrailerLink(rs.getString("trailer_link"));
				    obj.setThumbnailUrl(rs.getString("thumbnail_url"));
				    obj.setEmbeddedLink(rs.getString("embedded_link"));
				    list.add(obj);
				}

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        return list;
	    }

	public String SubmitReview(Review r) {
		String status = "";
		// ---------------------------------------------------------------------creating
		// connection with database
		Connection con;
		java.sql.PreparedStatement pst;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

			pst = con.prepareStatement("INSERT INTO Reviews ( username, movie_id, rating, review_text) VALUES (?,?,?,?)");

			pst.setString(1, r.getUsername());
			pst.setInt(2, r.getMovieId());
			pst.setInt(3, r.getRating());
			pst.setString(4, r.getReviewText());


			pst.executeUpdate();
			con.close();
			status = "Success";

		} catch (Exception e) {
			status = "Failed";
		}

		return status;
	}
	
	public String addNewMovie(Movie movie) {
		String status = "";
		// ---------------------------------------------------------------------creating
		// connection with database
		Connection con;
		java.sql.PreparedStatement pst;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

			pst = con.prepareStatement("INSERT INTO Movies (name, language, country, category, release_year, certificate, director, actors, actresses, music, platform, budget, collection, trailer_link, thumbnail_url, embedded_link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			   pst.setString(1, movie.getName());
	            pst.setString(2, movie.getLanguage());
	            pst.setString(3, movie.getCountry());
	            pst.setString(4, movie.getCategory());
	            pst.setInt(5, movie.getReleaseYear());
	            pst.setString(6, movie.getCertificate());
	            pst.setString(7, movie.getDirector());
	            pst.setString(8, movie.getActors());
	            pst.setString(9, movie.getActresses());
	            pst.setString(10, movie.getMusic());
	            pst.setString(11, movie.getPlatform());
	            pst.setDouble(12, movie.getBudget());
	            pst.setDouble(13, movie.getCollection());
	            pst.setString(14, movie.getTrailerLink());
	            pst.setString(15, movie.getThumbnailUrl());
	            pst.setString(16, movie.getEmbeddedLink());
		


			pst.executeUpdate();
			con.close();
			status = "Success";

		} catch (Exception e) {
			status = "Failed";
		}

		return status;
	}

	public String deleteMovie(String name, Integer movieId) {
		
		String status = "";
	
		Connection con;
		java.sql.PreparedStatement pst;
		
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
				con = DriverManager.getConnection(
						"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection

	      
	            String deleteQuery = "DELETE FROM Movies WHERE movie_id = ?";
	            PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);
	            preparedStatement.setInt(1, movieId);
        
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            con.close();
				status = "Success";
	        } catch (Exception e) {
				status = "Failed";
			} 
		 return status;
	}
	
}
