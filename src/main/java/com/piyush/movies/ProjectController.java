package com.piyush.movies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.piyush.movies.entities.Movie;
import com.piyush.movies.entities.Review;
import com.piyush.movies.entities.User;
import com.piyush.movies.services.MovieServices;
import com.piyush.movies.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectController {
	@Autowired
	UserServices us;
	@Autowired
	MovieServices ms;
	
	
	@GetMapping("/")
	public String home() {
		return "index.jsp";
	}
	
	@GetMapping("/UserReport")
	public ModelAndView employeeReport() {
		ModelAndView mv = new ModelAndView();

		ArrayList<User> userList = us.generateUserReport();
		mv.setViewName("UserReport.jsp");
		mv.addObject("userobj", userList);
		return mv; // jate time arraylist le jao ....

	}
	@GetMapping("/MoviesReport")
	public ModelAndView moviesReport(HttpSession ses) {
	    ModelAndView mv = new ModelAndView();

	    ArrayList<Movie> movieList = ms.generateMovieReport();

	    if ("admin".equals(ses.getAttribute("username"))) {
	        mv.setViewName("AdminMoviesReport.jsp");
	    } else {
	        mv.setViewName("UserMoviesReport.jsp");
	        
	        
	        // Check for the session attribute to show the pop-up
	        Boolean reviewAdded = (Boolean) ses.getAttribute("reviewAdded");
	        if (reviewAdded != null && reviewAdded) {
	            mv.addObject("showReviewAddedPopup", true);
	            ses.setAttribute("reviewAdded", false); // Reset the session attribute
	        }
	    }
	    
	    mv.addObject("movieobj", movieList);
	    return mv;
	}


	
	@GetMapping("/filterMovies")
	public ModelAndView filterMoviesReport(@RequestParam("category") String category,
	                                       @RequestParam("platform") String platform ,HttpSession ses) {
	    ModelAndView mv = new ModelAndView();

	    ArrayList<Movie> movieList = ms.filterMovies(category, platform);

	    // Add the filter result message to the ModelAndView
	    String filterMessage = "Filter results for category: '" + category + "', platform: '" + platform+"'";
	    mv.addObject("filterMessage", filterMessage);
	    
	    if ("admin".equals(ses.getAttribute("username"))) {
	    	 
	    	 mv.setViewName("AdminMoviesReport.jsp");
			}
			else {	 
				mv.setViewName("UserMoviesReport.jsp");
			}
			mv.addObject("movieobj", movieList);
			return mv; // jate time arraylist le jao ....

	}

	@GetMapping("/SearchMovies")
	public ModelAndView SearchMoviesReport(@RequestParam("movieName") String movieName ,HttpSession ses
	                                       ) {
	    ModelAndView mv = new ModelAndView();

	    ArrayList<Movie> movieList = ms.searchMovies(movieName);

	    // Add the filter result message to the ModelAndView
	    String SearchMessageForAdminSearch = "Search results for movie: '" + movieName + "'";
	    String SearchMessageForUserSearch = "13% viewers of your age group liked this movie";
	    String goToMoviesReportMsg = "Recommended";
	    
	    if(ses.getAttribute("username").equals("admin")) {
	    	
	    	 mv.addObject("filterMessage", SearchMessageForAdminSearch);
	    	 mv.addObject("goToMoviesReportMsg", goToMoviesReportMsg);
	    	 mv.setViewName("AdminMoviesReport.jsp");
			}
			else {
		
				  mv.addObject("filterMessage", SearchMessageForUserSearch);
				  mv.addObject("goToMoviesReportMsg", goToMoviesReportMsg);
				mv.setViewName("UserMoviesReport.jsp");
			}
			mv.addObject("movieobj", movieList);
			return mv; // jate time arraylist le jao ....

	    
	}
	@PostMapping("/addMovie")
    public String addNewMovie(
            Movie newMovie,
            HttpSession ses) {

		String status = ms.addNewMovie(newMovie);
	
	    if (status.equals("Success")) {
	        ses.setAttribute("movieAdddedSuccess", true);
	        return "redirect:/Admin.jsp"; 
	    } else {
	        // Password change failed
	        return "Failed.jsp"; 
	    }
		
    }
	@PostMapping("/deletemovie")
	public String deletemovie(String name, Integer movieId, HttpSession ses) {
	    String status = ms.deleteMovie(name , movieId);

	    if ("Success".equals(status)) {
	        
	        return "redirect:/MoviesReport";
	    }
	    return "Failed.jsp";
	}

	@PostMapping("/SubmitReview")
	public String submitReview(Review r, HttpSession ses) {
	    String status = ms.SubmitReview(r);

	    if ("Success".equals(status)) {
	        ses.setAttribute("reviewAdded", true); // Set session attribute
	        return "redirect:/MoviesReport";
	    }
	    return "Failed.jsp";
	}

	
	@PostMapping("/login")
	public String login(String username, String password, HttpSession ses) {
	    String stat = "";
	    // UserServices us = new UserServices(); @autowired akr diya hai.
	    stat = us.checkUser(username, password);
	    ses.setAttribute("username", username);
	    ses.setAttribute("password", password);
	    ses.setAttribute("fromLogin", "true");
	    if (stat.equals("User.jsp")) { // Use .equals() instead of ==
	    	 return "redirect:/MoviesReport";
	    }
	    return stat;
	}
	
	@GetMapping("/logout")
	public String login(HttpSession ses) {
	   
	
	    ses.setAttribute("username", null);
	    ses.setAttribute("password", null);
	
	   
	    return "index.jsp";
	}

	@PostMapping("/register") // jab html form submit hoga toh hum post mapping call karenge
	public String registerUser(User u , HttpSession ses , String username, String password) {
		String status = us.addNewUser(u);

		if (status.equals("Success")) {
			ses.setAttribute("username", username);
			ses.setAttribute("password", password);
			return "redirect:/MoviesReport";
		} else {
			return "Failed.jsp";
		}
	}

 // jab html form submit hoga toh hum post mapping call karenge
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("username") String username,
	                             @RequestParam("newPassword") String newPassword,
	                             HttpSession ses) {
	    boolean passwordChangeSuccess = us.changePassword(username, newPassword);



	    if (passwordChangeSuccess) {
	        ses.setAttribute("passwordChangeSuccess", true);
	        return "redirect:/index.jsp"; 
	    } else {
	        // Password change failed
	        return "Failed.jsp"; 
	    }



	}
	

}
 






	
	

