package com.piyush.movies.entities;

import java.util.ArrayList;

public class Movie {
	private int movieId;
    private String name;
    private String language;
    private String country;
    private String category;
    private int releaseYear;
    private String certificate;
    private String director;
    private String actors;
    private String actresses;
    private String music;
    private String platform;
    private double budget;
    private double collection;
    private String trailerLink;
    private String thumbnailUrl;
    private String embeddedLink;
  
    
    
    
    private ArrayList<Review> reviews;

    // ... (other methods and constructors)

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    

	public String getEmbeddedLink() {
		return embeddedLink;
	}
	public void setEmbeddedLink(String embeddedLink) {
		this.embeddedLink = embeddedLink;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getActresses() {
		return actresses;
	}
	public void setActresses(String actresses) {
		this.actresses = actresses;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getCollection() {
		return collection;
	}
	public void setCollection(double collection) {
		this.collection = collection;
	}
	public String getTrailerLink() {
		return trailerLink;
	}
	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
    
    
    
    
    
    
}
