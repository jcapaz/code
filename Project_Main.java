
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Project_Main {
	public static void main(String[] args) throws Exception {
		try{
			getConnection();
			createTables();
			insertToTables();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try { if(rs!= null) rs.close(); } catch (SQLException sqlEx) { /* ignored */ }
			try { if(stmt!= null) stmt.close(); } catch (SQLException sqlEx) { /* ignored */ }
			try { if(con!=null) con.close(); } catch (SQLException sqlEx) { /* ignored */ }
		}
	}
	/******************************************************************
	 *						PARAMETER SECTION
	 ******************************************************************/
	//MYSQL connection parameters
	private static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8";
	private static final String user = "root"; 
	private static final String password = "root";
	//Connection variables
	private static Connection con;
	private static ResultSet rs;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	/******************************************************************
	 *							MAIN METHODS
	 ******************************************************************/
	//Connect to SQL database
	public static void getConnection() throws Exception{
		System.out.println("Connecting to local database...");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		    System.out.println("Database connected successfully");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	//Call all table creations
	public static void createTables() throws Exception{
		System.out.println("Creating tables...");
		createMovies();
		createTags();
		createMovieGenres();
		createMovieDirectors();
		createMovieActors();
		createMovieCountries();
		createMovieLocations();
		createMovieTags();
		createUserTaggedMoviesTimestamps();
		createUserTaggedMovies();
		createUserRatedMoviesTimestamps();
		createUserRatedMovies();
		System.out.println("Tables created successfully");
	}
	//Call all table insertions
	public static void insertToTables() throws Exception{
		System.out.println("Inserting to tables...");
		insertMovies();
		insertTags();
		insertMovieGenres();
		insertMovieDirectors();
		insertMovieActors();
		insertMovieCountries();
		insertMovieLocations();
		insertMovieTags();
		insertUserTaggedMoviesTimestamps();
		insertUserTaggedMovies();
		insertUserRatedMoviesTimestamps();
		insertUserRatedMovies();
		System.out.println("Insertions completed successfully");
	}
	/******************************************************************
	 *						CREATE TABLES SECTION
	 ******************************************************************/
	public static void createMovies(){
		try{
			stmt = con.createStatement();
			String query = "create table movies ("
					+ "id 						int NOT NULL,"
					+ "title 					VARCHAR(150),"
					+ "imdbID 					int,"
					+ "spanishTitle 			VARCHAR(150),"
					+ "imdbPictureURL		 	VARCHAR(150),"
					+ "year 					int,"
					+ "rtID 					VARCHAR(100),"
					+ "rtAllCriticsRating 		double,"
					+ "rtAllCriticsNumReviews 	int,"
					+ "rtAllCriticsNumFresh 	int,"
					+ "rtAllCriticsNumRotten 	int,"
					+ "rtAllCriticsScore 		double,"
					+ "rtTopCriticsRating 		double,"
					+ "rtTopCriticsNumReviews 	int,"
					+ "rtTopCriticsNumFresh 	int,"
					+ "rtTopCriticsNumRotten 	int,"
					+ "rtTopCriticsScore 		double,"
					+ "rtAudienceRating 		double,"
					+ "rtAudienceNumRatings		int,"
					+ "rtAudienceScore			double,"
					+ "rtPictureURL				VARCHAR(150),"
					+ "PRIMARY KEY (id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createTags(){
		try{
			stmt = con.createStatement();
			String query = "create table tags ("
					+ "id 						int NOT NULL,"
					+ "value 					VARCHAR(50),"
					+ "PRIMARY KEY (id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createMovieGenres(){
		try{
			stmt = con.createStatement();
			String query = "create table movie_genres ("
					+ "movieID 					int NOT NULL,"
					+ "genre 					VARCHAR(50),"
					+ "PRIMARY KEY (movieID, genre),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createMovieDirectors(){
		try{
			stmt = con.createStatement();
			String query = "create table movie_directors ("
					+ "movieID 					int NOT NULL,"
					+ "directorID				VARCHAR(50) NOT NULL,"
					+ "directorName				VARCHAR(50),"
					+ "PRIMARY KEY (movieID, directorID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createMovieActors(){
		try{
			stmt = con.createStatement();
			String query = "create table movie_actors ("
					+ "movieID 					int NOT NULL,"
					+ "actorID					VARCHAR(50) NOT NULL,"
					+ "actorName				VARCHAR(50),"
					+ "ranking					int,"
					+ "PRIMARY KEY (movieID, actorID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createMovieCountries(){
		try{
			stmt = con.createStatement();
			String query = "create table movie_countries ("
					+ "movieID 					int NOT NULL,"
					+ "country					VARCHAR(50) NOT NULL,"
					+ "PRIMARY KEY (movieID, country),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createMovieLocations(){
		try{
			stmt = con.createStatement();
			String query = "create table movie_locations ("
					+ "movieID 					int NOT NULL,"
					+ "location1				VARCHAR(150),"
					+ "location2				VARCHAR(150),"
					+ "location3				VARCHAR(150),"
					+ "location4				VARCHAR(150),"
					+ "PRIMARY KEY (movieID, location1, location2, location3, location4),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createMovieTags(){
		try{
			stmt = con.createStatement();
			String query = "create table movie_tags ("
					+ "movieID 					int NOT NULL,"
					+ "tagID					int NOT NULL,"
					+ "tagWeight				int,"
					+ "PRIMARY KEY (movieID, tagID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id),"
					+ "FOREIGN KEY (tagID) REFERENCES tags(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createUserTaggedMoviesTimestamps(){
		try{
			stmt = con.createStatement();
			String query = "create table user_taggedmoviestimestamps ("
					+ "userID 					int NOT NULL,"
					+ "movieID					int NOT NULL,"
					+ "tagID					int NOT NULL,"
					+ "timestamp				int,"
					+ "PRIMARY KEY (userID, movieID, tagID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id),"
					+ "FOREIGN KEY (tagID) REFERENCES tags(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createUserTaggedMovies(){
		try{
			stmt = con.createStatement();
			String query = "create table user_taggedmovies ("
					+ "userID 					int NOT NULL,"
					+ "movieID					int NOT NULL,"
					+ "tagID					int NOT NULL,"
					+ "date_day					int,"
					+ "date_month				int,"
					+ "date_year				int,"
					+ "date_hour				int,"
					+ "date_minute				int,"
					+ "date_second				int,"
					+ "PRIMARY KEY (userID, movieID, tagID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id),"
					+ "FOREIGN KEY (tagID) REFERENCES tags(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createUserRatedMoviesTimestamps(){
		try{
			stmt = con.createStatement();
			String query = "create table user_ratedmoviestimestamps ("
					+ "userID 					int NOT NULL,"
					+ "movieID					int NOT NULL,"
					+ "rating					double,"
					+ "timestamp				int,"
					+ "PRIMARY KEY (userID, movieID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	public static void createUserRatedMovies(){
		try{
			stmt = con.createStatement();
			String query = "create table user_ratedmovies ("
					+ "userID 					int NOT NULL,"
					+ "movieID					int NOT NULL,"
					+ "rating					double,"
					+ "date_day					int,"
					+ "date_month				int,"
					+ "date_year				int,"
					+ "date_hour				int,"
					+ "date_minute				int,"
					+ "date_second				int,"
					+ "PRIMARY KEY (userID, movieID),"
					+ "FOREIGN KEY (movieID) REFERENCES movies(id)"
					+ ");";
			stmt.executeUpdate(query);
		}catch(Exception e){ e.printStackTrace(); }
	}
	/******************************************************************
	 *					INSERT TO TABLES SECTION
	 ******************************************************************/
	public static void insertMovies() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movies.dat")));
		try{
			pstmt = con.prepareStatement("insert into movies values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			String line = in.readLine();
			//Insert to movies
			while ((line = in.readLine()) != null) {
				String[] content = line.split("\\t", -1);
				for(int i = 0; i < content.length; i++){
					if(content[i].equals("\\N") || content[i].equals("")){
						content[i] = null;
					}
				}
				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.setString(5, content[4]);
				pstmt.setString(6, content[5]);
				pstmt.setString(7, content[6]);
				pstmt.setString(8, content[7]);
				pstmt.setString(9, content[8]);
				pstmt.setString(10, content[9]);
				pstmt.setString(11, content[10]);
				pstmt.setString(12, content[11]);
				pstmt.setString(13, content[12]);
				pstmt.setString(14, content[13]);
				pstmt.setString(15, content[14]);
				pstmt.setString(16, content[15]);
				pstmt.setString(17, content[16]);
				pstmt.setString(18, content[17]);
				pstmt.setString(19, content[18]);
				pstmt.setString(20, content[19]);
				pstmt.setString(21, content[20]);
				pstmt.executeUpdate();
			}
			
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertTags() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/tags.dat")));
		try{
			pstmt = con.prepareStatement("insert into tags values(?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertMovieGenres() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movie_genres.dat")));
		try{
			pstmt = con.prepareStatement("insert into movie_genres values(?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertMovieDirectors() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movie_directors.dat")));
		try{
			pstmt = con.prepareStatement("insert into movie_directors values(?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertMovieActors() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movie_actors.dat")));
		try{
			pstmt = con.prepareStatement("insert into movie_actors values(?, ?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertMovieCountries() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movie_countries.dat")));
		try{
			pstmt = con.prepareStatement("insert into movie_countries values(?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertMovieLocations() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movie_locations.dat")));
		try{
			pstmt = con.prepareStatement("insert into movie_locations values(?, ?, ?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.setString(5, content[4]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertMovieTags() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/movie_tags.dat")));
		try{
			pstmt = con.prepareStatement("insert into movie_tags values(?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertUserTaggedMoviesTimestamps() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/user_taggedmovies-timestamps.dat")));
		try{
			pstmt = con.prepareStatement("insert into user_taggedmoviestimestamps values(?, ?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertUserTaggedMovies() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/user_taggedmovies.dat")));
		try{
			pstmt = con.prepareStatement("insert into user_taggedmovies values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);
			
				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.setString(5, content[4]);
				pstmt.setString(6, content[5]);
				pstmt.setString(7, content[6]);
				pstmt.setString(8, content[7]);
				pstmt.setString(9, content[8]);
				pstmt.executeUpdate();
			}
		}
		catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertUserRatedMoviesTimestamps() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/user_ratedmovies-timestamps.dat")));
		try{
			pstmt = con.prepareStatement("insert into user_ratedmoviestimestamps values(?, ?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);

				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.executeUpdate();
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
	public static void insertUserRatedMovies() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(new File("E:/DB Project/Rotten Tomatos Dataset/user_ratedmovies.dat")));
		try{
			pstmt = con.prepareStatement("insert into user_ratedmovies values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			String line = in.readLine();
			while ((line = in.readLine()) != null) {
				
				String[] content = line.split("\\t", -1);
			
				pstmt.setString(1, content[0]);
				pstmt.setString(2, content[1]);
				pstmt.setString(3, content[2]);
				pstmt.setString(4, content[3]);
				pstmt.setString(5, content[4]);
				pstmt.setString(6, content[5]);
				pstmt.setString(7, content[6]);
				pstmt.setString(8, content[7]);
				pstmt.setString(9, content[8]);
				pstmt.executeUpdate();
			}
		}
		catch(Exception e){ e.printStackTrace(); }
		finally{ if(in != null){ in.close(); }
		}
	}
}