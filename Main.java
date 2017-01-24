import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Main extends Application {
	//@Override
	ScrollPane table = new ScrollPane();
	Font font = new Font("Lucida Console", 15);
	private static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8";
	private static final String user = "root"; 
	private static final String password = "root";
	//Connection variables
	private static Connection con;
	GridPane side = new GridPane();
	
	public void start(Stage primaryStage) {
		try {
			getConnection();
			table.setMinViewportHeight(40);
			BorderPane bpane = new BorderPane();
			Scene scene = new Scene(bpane,1000,800);
			HBox hbox = new HBox();
			hbox.setStyle("-fx-background-color:#ff0000;");
			//hbox top
			Image logo = new Image("MOVIE_SQL_logo.png");
			ImageView img = new ImageView(logo);
			img.setFitWidth(128);
			img.setPreserveRatio(true);
			side.add(new Text("                      "), 0, 0);
			side.minWidth(300);
			
			TextField input = new TextField("    Enter Keywords    ");
			input.setMinWidth(250);
			
			input.setOnMousePressed(new EventHandler<MouseEvent> () {
				@Override
				public void handle(MouseEvent event) {
					input.clear(); 
		           } 
		       });

			Button actor = new Button("Actor");
			actor.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query(input.getText(),"Actor"));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Button director = new Button("Director");
			director.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query(input.getText(),"Director"));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Button movieusertags = new Button("Movie User Tags");
			movieusertags.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query(input.getText(),"Movie User Tags"));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Button movietags = new Button("Movie Tags");
			movietags.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query(input.getText(),"Movie Tags"));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Button tags = new Button("Tags");
			tags.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query(input.getText(),"Tags"));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Button user = new Button("User");
			user.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query(input.getText(),"User"));
						table.setContent(createTable(rs));
						
						ResultSet rs2 = stmt.executeQuery(query(input.getText(),"User2"));
						
						side.add(createTable(rs2),0,0);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			hbox.getChildren().addAll(img, input, actor, director, movieusertags, movietags, tags, user);
			hbox.setAlignment(Pos.BOTTOM_LEFT);
			
			//hbox left
			VBox vbox = new VBox();
			vbox.setStyle("-fx-background-color:#ff0000; -fx-border-size:10px; -fx-border-color: #ff0000;");
			Text t1 = new Text("See the Top 10 \n   Actors in \n   X movies");
			t1.getStyleClass().add("Text");
			TextField tf1 = new TextField("X");
			tf1.setMaxWidth(40);
			tf1.setOnMousePressed(new EventHandler<MouseEvent>() { 
		           @Override 
		           public void handle(MouseEvent event) { 
		           tf1.clear();  
		           } 
		       }); 

			
			Button b1 = new Button("Go");
			b1.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query("Top Actors",Integer.parseInt(tf1.getText())));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			HBox hb1 = new HBox();
			hb1.getChildren().addAll(tf1,b1);	
			
			Text t2 = new Text("See the Top 10 " + "\n" + "  Directors in \n   X movies");
			t2.getStyleClass().add("Text");
			TextField tf2 = new TextField("X");
			tf2.setMaxWidth(40);
			tf2.setOnMousePressed(new EventHandler<MouseEvent>() { 
		           @Override 
		           public void handle(MouseEvent event) { 
		           tf2.clear();  
		           } 
		       });
			Button b2 = new Button("Go");
			b2.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query("Top Directors",Integer.parseInt(tf2.getText())));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			HBox hb2 = new HBox();
			hb2.getChildren().addAll(tf2,b2);	
			
			Text t3 = new Text("See the Top X" + "\n" + "    Movies");
			t3.getStyleClass().add("Text");
			TextField tf3 = new TextField("X");
			tf3.setMaxWidth(40);
			tf3.setOnMousePressed(new EventHandler<MouseEvent>() { 
		           @Override 
		           public void handle(MouseEvent event) { 
		           tf3.clear();  
		           } 
		       });
			Button b3 = new Button("Go");
			b3.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query("Top Movies",Integer.parseInt(tf3.getText())));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			HBox hb3 = new HBox();
			hb3.getChildren().addAll(tf3,b3);	
			
			Text t4 = new Text("See the Top X " + "\n" + "  Movies in \n   favorite genre ");
			t4.getStyleClass().add("Text");
			TextField genre = new TextField("Genre");
			genre.setMaxWidth(128);
			genre.setOnMousePressed(new EventHandler<MouseEvent>() { 
		           @Override 
		           public void handle(MouseEvent event) { 
		           genre.clear();  
		           } 
		       });		
			
			TextField tf4 = new TextField("X");
			tf4.setMaxWidth(40);
			tf4.setOnMousePressed(new EventHandler<MouseEvent>() { 
		           @Override 
		           public void handle(MouseEvent event) { 
		           tf4.clear();  
		           } 
		       });
			Button b4 = new Button("Go");
			b4.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e){
					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query("Genre",Integer.parseInt(tf4.getText()),genre.getText()));
						table.setContent(createTable(rs));
						side.add(new Text("                      "), 0, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			HBox hb4 = new HBox();
			hb4.getChildren().addAll(genre);
			HBox hb5 = new HBox();
			hb5.getChildren().addAll(tf4,b4);
			
			hb1.setAlignment(Pos.CENTER);
			hb2.setAlignment(Pos.CENTER);
			hb3.setAlignment(Pos.CENTER);
			
			vbox.getChildren().addAll(t1,hb1,t2,hb2,t3,hb3,t4,hb4,hb5);
			vbox.setSpacing(20);
			vbox.setAlignment(Pos.CENTER);
			
			
			//set Border Pane Values
			bpane.setLeft(vbox);
			bpane.setTop(hbox);
			bpane.setCenter(table);
			bpane.setRight(side);
			
			bpane.setStyle("-fx-background-color:white");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	/*public Text createTable(ResultSet rs) throws SQLException{
		//Call query
		Text test = new Text("");
		StringBuilder s = new StringBuilder(2000);
		while(rs.next()){
			for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
				if(rs.getMetaData().getColumnType(i) == Types.DOUBLE){
					s.append(rs.getDouble(i) + "|\t");
				}
				else if(rs.getMetaData().getColumnType(i) == Types.VARCHAR){
					s.append(rs.getString(i) + "|\t\t\t");
				}
				else if(rs.getMetaData().getColumnType(i) == Types.INTEGER){
					s.append(rs.getInt(i) + "|\t");
				}
				else if(rs.getMetaData().getColumnType(i) == Types.DECIMAL){
					s.append(rs.getDouble(i) + "|\t");
				}
				else{
					System.out.println(rs.getMetaData().getColumnType(i));
					s.append("No Information!|\t");
				}
			}
			test.setText(test.getText() + s.toString() + "\n");
			s = new StringBuilder(2000);
		}
		return test;
	}
*/
	public GridPane createTable(ResultSet rs){
		  //Call query
		  GridPane gPane = new GridPane();
		  int row = 0;
		  try{
		    while(rs.next()){
		      for(int i = 1; i <= rs.getMetaData().getColumnCount();i++){
		        if(rs.getMetaData().getColumnType(i)  == Types.DOUBLE){
		          gPane.add(new Text(" " + rs.getDouble(i)),i,row);
		        }

		        else if(rs.getMetaData().getColumnType(i) == Types.VARCHAR){
		          String s = rs.getString(i);
		          if(s != null && s.length() > 4){
		        	if(s.substring(0,4).equals("http")){
		        		Hyperlink h = new Hyperlink(s);
		        		h.setOnAction(new EventHandler<ActionEvent>() {
		                    @Override
		                    public void handle(ActionEvent e) {
		                        try{
		                        	Desktop.getDesktop().browse(new URI(s));
		                        }catch(Exception e1){
		                        	System.out.print(e1);
		                        }                    }
		                });
		        		gPane.add(h,i,row);
		        	}
			        else{
			            gPane.add(new Text(s),i,row);
			        }
		          }
		          else{
			            gPane.add(new Text(s),i,row);
			        }
		        }

		        else if(rs.getMetaData().getColumnType(i) == Types.INTEGER){
		          gPane.add(new Text(" "+rs.getInt(i)),i,row);
		        }

		        else if(rs.getMetaData().getColumnType(i) == Types.DECIMAL){
		          gPane.add(new Text(" "+rs.getDouble(i)),i,row);
		        }
		      }
		      row++;
		    }
		  }catch(Exception e) {
		    e.printStackTrace();
		  }
		  return gPane;
	}

	public String query(String type, int num){
		String retQuery = "";
		if(type.equals("Top Movies")){
			
			retQuery = "Select distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, "
					+ "M.imdbPictureURL From MOVIES M Order by M.rtAudienceScore desc, M.title Limit " + num + ";";
		}
		else if(type.equals("Top Actors")){
			
			retQuery = "Select A.actorName, AVG(M.rtAudienceScore) From MOVIE_ACTORS A, MOVIES M "
					+ "Where A.movieID = M.id Group by A.actorName Having COUNT(A.movieID) >= " + num + " "
					+ "Order by AVG(M.rtAudienceScore) desc Limit 10;";
		}
		else if(type.equals("Top Directors")){
			
			retQuery = "Select D.directorName, AVG(M.rtAudienceScore) "
					+ "From MOVIE_DIRECTORS D, MOVIES M Where D.movieID = M.id Group by D.directorName "
					+ "Having COUNT(D.movieID) >= " + num + " Order by AVG(M.rtAudienceScore) desc Limit 10;";
		}
		return retQuery;
	}
	
	//query for genre
	public String query(String type,int num, String search){
		String retQuery = "";
		if(type.equals("Genre")){
			retQuery = "Select M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL "
					+ "From MOVIES M, movie_genres G Where M.id=G.movieID AND G.genre=\'" + search + "\' "
					+ "Order by M.rtAudienceScore desc Limit " + num + ";";
		}
		return retQuery;
	}
	
	public String query(String search,String type){
		String retQuery = "";
		if(type.equals("Movie User Tags")){
			retQuery = "Select M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL, T.value "
					+ "From MOVIES M, USER_TAGGEDMOVIES UT, TAGS T "
					+ "Where M.id=UT.movieID AND UT.tagID=T.id AND M.title LIKE \'%" + search + "%\' "
					+ "Order by M.title;";
		}
		
		else if(type.equals("Actor")){
			retQuery = "Select M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL, A.actorName "
					+ "From MOVIES M, MOVIE_ACTORS A Where M.id=A.movieID AND A.actorName LIKE \'%" + search + "%\' "
					+ "Order by M.title;";
		}
		
		else if(type.equals("Director")){
			retQuery = "Select M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL, D.directorName "
					+ "From MOVIES M, MOVIE_DIRECTORS D Where M.id=D.movieID AND D.directorName LIKE \'%" + search + "%\' "
					+ "Order by M.title;";
		}
		
		else if(type.equals("Movie Tags")){
			retQuery = "Select T.value From MOVIES M, TAGS T , MOVIE_TAGS MT " 
					+  "Where M.id=MT.movieID and MT.tagID = T.id AND M.title = \'" + search + "\';";
		}
		
		else if(type.equals("Tags")){
			retQuery = "Select M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL "
					+ "From MOVIES M, TAGS T, MOVIE_TAGS MT "
					+ "Where M.id=MT.movieID and MT.tagID = T.id AND T.value LIKE \'%" + search + "%\' "
					+ "Order by M.rtAudienceScore desc;";
		}
		
		else if(type.equals("User")){
			retQuery = "Select M.title, UR.rating From MOVIES M, USER_RATEDMOVIESTIMESTAMPS UR "
					+ "Where UR.movieID = M.id AND UR.userID = " + search + " Group by Ur.timestamp Order by UR.timestamp;";
		}
		else if(type.equals("User2")){
			retQuery = "Select MG.genre, (100 * (COUNT(MG.genre)/(Select COUNT(MG.genre) From MOVIES M, user_ratedmoviestimestamps UR, "
					+ "MOVIE_GENRES MG Where UR.movieID = M.id AND UR.userID = 75 AND UR.movieID = MG.movieID))) "
					+ "From MOVIES M, user_ratedmoviestimestamps UR, MOVIE_GENRES MG "
					+ "Where UR.movieID = M.id AND UR.userID = " + search + " AND UR.movieID = MG.movieID Group by MG.genre;";
		}
		return retQuery;
	}
	
	//took this from Project_Main
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
}
