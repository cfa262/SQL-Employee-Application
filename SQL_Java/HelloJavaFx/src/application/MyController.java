package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class MyController implements Initializable {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField mInitField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField ssnField;
	@FXML
	private TextField bdateField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField sexField;
	@FXML
	private TextField salaryField;
	@FXML
	private TextField superssnField;
	@FXML
	private TextField dnoField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField projectField;
	@FXML
	private TextField hoursField; 
	@FXML
	private TextField dFirstNameField;
	@FXML
	private TextField essnField;
	@FXML
	private TextField dSexField;
	@FXML
	private TextField relationshipField;
	@FXML
	private TextField dbdateField;
	@FXML
	private TextField emplStatusField;
	@FXML
	private TextField projectStatusField;
	@FXML 
	private TextField dStatusField;
	@FXML
	private Button addEmplButton;
	@FXML
	private Button addProjectButton;
	@FXML
	private Button addDependentButton;
	@FXML
	private Button printReportButton;



	//to get employee ssn
	private String emplSsn = null;
	@FXML
	private CheckBox yesCheckBox;
	@FXML
	private CheckBox noCheckBox;

	//Driver Manager stuff
	private String url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";
	private String username = "calvare6";
	private String password = "ixoagl";
	private Connection conn = null;
	private int manssn1 = 333445555;
	private int manssn2 = 987654321;
	private int manssn3 = 888665555;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initiateConnection();
			
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO (don't really need to do anything here).
	}
	public void insertProject(ActionEvent event) throws SQLException {

		//this.conn = DriverManager.getConnection(url, username, password);

		String proX = new String("ProductX");
		String proY = new String("ProductY");
		String proZ = new String("ProductZ");
		BigDecimal pnum = new BigDecimal(1);

		BigDecimal dnum = new BigDecimal(5);
		int hrs = Integer.parseInt(hoursField.getText());

		String pStr = projectField.getText();
		if(pStr.equals(proX) || pStr.equals(proY) || pStr.equals(proZ)) {
			if(hrs > 40) {
				projectStatusField.setText("Hours cannot exceed 40");
			}else {
				String proField = projectField.getText();
				/*
				if(proField.equals(proX)) {pnum = new BigDecimal(1);}
				else if(proField.equals(proX)){pnum = new BigDecimal(2);}
				else if(proField.equals(proX)) {pnum = new BigDecimal(3);}
				//BigDecimal hours = new BigDecimal(Integer.parseInt(hoursField.getText()));
				String query = "insert into project values (?, ?, ?, ?)";
				PreparedStatement p = this.conn.prepareStatement(query);
				p.clearParameters();

				p.setString(1, proField);
				p.setBigDecimal(2, pnum);
				p.setString(3, "Bellaire");
				p.setBigDecimal(4, dnum);
				p.executeUpdate();
				 */


				insertWorksOn();
				projectStatusField.setText("ADDED!");
			}
		}
		else {
			projectStatusField.setText("Cannot add current project");
		}
		//getProject();

	}

	public void insertWorksOn() throws SQLException {

		this.conn = DriverManager.getConnection(url, username, password);

		String proField = projectField.getText();
		String proX = "ProductX";
		String proY = "ProductY";
		String proZ = "ProductZ";

		BigDecimal pnum = new BigDecimal(1);
		if(proField.equals(proX)) {pnum = new BigDecimal(1);}
		else if(proField.equals(proX)){pnum = new BigDecimal(2);}
		else if(proField.equals(proX)) {pnum = new BigDecimal(3);}

		String essn = this.emplSsn; 

		BigDecimal hours = new BigDecimal(Integer.parseInt(hoursField.getText()));
		String query = "insert into works_on values (?, ?, ?)";
		PreparedStatement p = this.conn.prepareStatement(query);
		p.clearParameters();

		p.setString(1, essn);
		p.setBigDecimal(2, pnum);
		p.setBigDecimal(3, hours);
		p.executeUpdate();

	}

	@FXML
	public void handleYesBox() {
		if(yesCheckBox.isSelected()){
			noCheckBox.setSelected(false);
		}
	}
	@FXML
	public void handleNoBox() {
		if(noCheckBox.isSelected()){
			yesCheckBox.setSelected(false);
		}
	}
		
	public void insertDependent(ActionEvent event) throws SQLException {

		//this.conn = DriverManager.getConnection(url, username, password);

			if(noCheckBox.isSelected()){
				dStatusField.setText("Cannot add dependent");
			}else if(yesCheckBox.isSelected()){
				String essn = essnField.getText();
				String dname = dFirstNameField.getText();
				String s = dSexField.getText();
				String rel = relationshipField.getText();
				java.sql.Date bdate = null;

				String query = "insert into dependent values (?, ?, ?, ?, ?)";
				PreparedStatement p = this.conn.prepareStatement(query);
				p.clearParameters();

				p.setString(1, essn);
				p.setString(2, dname);
				p.setString(3, s);
				p.setDate(4, bdate);
				p.setString(5, rel);

				p.executeUpdate();

				dStatusField.setText("Dep. Added!");
			}else {
				dStatusField.setText("Cannot add dependent");
			}
	}
	public void printReport(ActionEvent event) throws SQLException {

		System.out.println("PRINTING REPORT\n");

		this.conn = DriverManager.getConnection(url, username, password);

		String empstr = "select fname, minit, lname, ssn, address, sex, salary, superssn, dno from employee where ssn = "+111122555;
		//String projstr = "select pname, pnumber from project";
		String worksstr = "select essn, pno, hours, pname from employee, project, works_on where essn = "+111122555 +" AND pno = pnumber";
		String depstr = "select dependent_name, essn, relationship from dependent, employee where essn = ssn";
		String fname, minit, lname, ssn, address, sex, superssn;
		BigDecimal salary, dno;
		PreparedStatement e = this.conn.prepareStatement(empstr);
		e.clearParameters();
		ResultSet r = e.executeQuery();
		int i = 0;
		while(r.next() ) {
			fname = r.getString(1);
			minit = r.getString(2);
			lname = r.getString(3);
			ssn = r.getString(4);
			address = r.getString(5);
			sex = r.getString(6);
			salary = r.getBigDecimal(7);
			superssn = r.getString(8);
			dno = r.getBigDecimal(9);
			System.out.println(fname+" "+minit+" "+lname+" "+ssn+" "+address+" "+sex+" "+salary+" "+superssn+" "+dno);
			
		}

		String essn;
		BigDecimal pno;
		BigDecimal hours;
		String pname;
		PreparedStatement w = this.conn.prepareStatement(worksstr);
		w.clearParameters();
		ResultSet c = w.executeQuery();

		while(c.next() && i==0) {
			essn = c.getString(1);
			pno = c.getBigDecimal(2);
			hours = c.getBigDecimal(3);
			pname = c.getString(4);
			System.out.println(essn+" "+pno+ " "+hours+" "+pname);
			i=1;
		}
		
		String dessn;
		String dname;
		String rel;
		PreparedStatement d = this.conn.prepareStatement(depstr);
		d.clearParameters();
		ResultSet q = d.executeQuery();

		while(q.next()&& i==1) {
			dname = q.getString(1);
			dessn = q.getString(2);
			rel = q.getString(3);
			System.out.println(dname+" "+dessn+ " "+rel);
			i=0;
		}

	}
	public void initiateConnection() throws SQLException, IOException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver cannot be loaded");
		}
		//this.conn = DriverManager.getConnection(url, username, password);

	}
	public void insertEmployee(ActionEvent event) throws SQLException, IOException, ParseException {

		System.out.println("Inserting Employee\n");

		addEmployees();

		emplStatusField.setText("Empl. Added!");

		//getEmployees();

	}
	public void updateEmp() throws SQLException, ParseException, IOException {
		this.conn = DriverManager.getConnection(url, username, password);

		BigDecimal dec = new BigDecimal(55000);

		//String startDate="12-31-2014";
		//SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		//java.util.Date date = sdf1.parse(startDate);
		//java.sql.Date bdate = (java.sql.Date) new Date(date.getTime());

		String query = "insert into employee values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//String query = "INSERT INTO employee VALUES ('Cal','F','Alv','123456789','10-NOV-27','Houston,TX','M' ,55000,'888665555',null,null)";
		PreparedStatement p = this.conn.prepareStatement(query);

		p.clearParameters();
		p.setString(1, "Calvin");
		p.setString(2, "F");
		p.setString(3, "Alvarez");
		p.setString(4, "123456777");
		p.setDate(5, null);
		p.setString(6, "Fairfax, VA");
		p.setString(7, "M");
		p.setBigDecimal(8, dec);
		p.setString(9, "888665555");
		p.setBigDecimal(10, null);
		p.setString(11, null);

		//p.executeQuery();
		p.executeUpdate();
		//p.clearParameters();

	}

	public void addEmployees() throws SQLException, IOException, ParseException{

		this.conn = DriverManager.getConnection(url, username, password);

		String fname = firstNameField.getText();
		System.out.println(fname);
		String minit = mInitField.getText();
		String lname = lastNameField.getText();
		String ssn = ssnField.getText();
		this.emplSsn = ssnField.getText();

		//String startDate="12-31-2014";
		//SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		//java.util.Date date = sdf1.parse(startDate);
		//java.sql.Date bdate = (java.sql.Date) new Date(date.getTime());


		//SimpleDateFormat d = null;
		java.sql.Date bdate = null;
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		//java.sql.Date bdate = new java.sql.Date(sdf.get);
		String address = addressField.getText();
		String sex = sexField.getText();

		BigDecimal salary = new BigDecimal(Integer.parseInt(salaryField.getText()));
		System.out.println(salary);

		String superssn = superssnField.getText();

		//BigDecimal d = new BigDecimal(10);
		//int d = Integer.parseInt(dnoField.getText());
		BigDecimal dno = new BigDecimal(Integer.parseInt(dnoField.getText()));
		//System.out.println(dno);
		//BigDecimal dno = null;

		String email = emailField.getText();
		//String email = null;

		String query = "insert into employee values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//String query = "INSERT INTO employee VALUES ('Cal','F','Alv',null,null,null,null,null,null,null,null)";
		PreparedStatement p = this.conn.prepareStatement(query);
		p.clearParameters();

		p.setString(1, fname);
		p.setString(2, minit);
		p.setString(3, lname);
		p.setString(4, ssn);
		p.setDate(5, bdate);
		p.setString(6, address);
		p.setString(7, sex);
		p.setBigDecimal(8, salary);
		p.setString(9, superssn);  //888665555
		p.setBigDecimal(10, dno);
		p.setString(11, email);

		p.executeUpdate();

		System.out.println("WORKS!");

	}
	public void getEmployees() throws SQLException, IOException {

		this.conn = DriverManager.getConnection(url, username, password);

		String fname, minit, lname;
		String str = "select fname, minit, lname from employee";
		//+ "from employee, project, works_on "
		//+ "where plocation = 'Houston' and pname = 'ProductZ' and pnumber = pno and essn = ssn "; //Research department relates to dno = 5

		PreparedStatement p = this.conn.prepareStatement(str);
		p.clearParameters();
		ResultSet r = p.executeQuery();

		while(r.next()) {
			fname = r.getString(1);
			minit = r.getString(2);
			lname = r.getString(3);
			System.out.println(fname+" "+minit+" "+lname);
		}
	}

}