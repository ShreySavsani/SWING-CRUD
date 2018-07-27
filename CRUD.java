import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
	
class CRUD extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	JTextArea t1,t2;
	JTextField tf1;
	JPasswordField pf;
	String username,pass,query;
	Connection cn;
  Statement stmt;
    ResultSet res;
	//Font f=new Font("Lucida Bright",Font.BOLD,20);
	CRUD()
	{
		setVisible(true);
		setLayout(null);
		setSize(1000,1000);

		l1=new JLabel("Username:");
		l1.setBounds(200,50,110,40);
		//l1.setFont(f);
		add(l1);

		tf1=new JTextField();
		
		tf1.setBounds(470,50,200,40);
		//tf1.setFont(f);
		add(tf1);

		l2=new JLabel("Password:");
		l2.setBounds(200,130,110,40);
		//l2.setFont(f);
		add(l2);

		pf=new JPasswordField();
		pf.setBounds(470,130,200,40);
		//pf.setFont(f);
		add(pf);
		
		b1=new JButton("Connect");
		b1.setBounds(200,200,180,40);
		//b1.setFont(f);
		b1.addActionListener(this);

		add(b1);

		b2=new JButton("Disconnect");
		b2.setBounds(490,200,180,40);
    b2.setEnabled(false);
		//b2.setFont(f);
		b2.addActionListener(this);
	
		add(b2);
	

		t1=new JTextArea();
		t1.setBounds(130,280,630,150);
		//t1.setFont(f);
		add(t1);


		b3=new JButton("Select");
		b3.setBounds(130,450,180,40);
    b3.setEnabled(false);	
  //	b3.setFont(f);
		b3.addActionListener(this);
		
		add(b3);


		b4=new JButton("Update");
		b4.setBounds(350,450,180,40);
		b4.setEnabled(false);
    //b4.setFont(f);
		b4.addActionListener(this);
		
		add(b4);

		b5=new JButton("Insert");
		b5.setBounds(580,450,180,40);
    b5.setEnabled(false);
		//b5.setFont(f);
		b5.addActionListener(this);
		
		add(b5);

		b6=new JButton("Delete");
		b6.setBounds(130,500,180,40);
		b6.setEnabled(false);
    //b6.setFont(f);
		b6.addActionListener(this);
		
		add(b6);

		b7=new JButton("Clear Query");
		b7.setBounds(350,500,180,40);
		b7.setEnabled(false);
    //b7.setFont(f);
		b7.addActionListener(this);
		
		add(b7);

		b8=new JButton("Clear Result");
		b8.setBounds(580,500,180,40);
		b8.setEnabled(false);
    //b8.setFont(f);
		b8.addActionListener(this);
	
		add(b8);



		t2=new JTextArea();
		t2.setBounds(130,570,630,250);
		//t2.setFont(f);
		add(t2);



	}
	 
    
    @Override
    public void actionPerformed(ActionEvent e)
     {
      String s=e.getActionCommand();
      if(s=="Connect"){
         initializeDB(); 
      }
      if(s=="Select"){
          select();
      }
      if(s=="Insert"){
          insert();
      }
      if(s=="Delete"){
       delete();
      }
      if(s=="Update"){
          update();
      }
      if(s=="Clear Query")
      {
          clearquery();
    }
      if(s=="Clear Result"){
          clearres();
      }
      if(s=="Disconnect"){
          disconnect();
      }
    }
    public static void main(String[] args)
    {
        CRUD p=new CRUD();
        p.setSize(1000,1000);
        p.setVisible(true);
        p.setTitle("CRUD");
    }

    private void initializeDB() 
    {
       String user=tf1.getText();
       String pass=pf.getText();
        
          try{
         Class.forName("com.mysql.jdbc.Driver");
            cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/store", user,pass);
            stmt=cn.createStatement();
            if(cn!=null)
            {
                 JOptionPane.showMessageDialog(this,"Database connection successfully...");      
                 b2.setEnabled(true);
                 b3.setEnabled(true);
                 b4.setEnabled(true);
                 b5.setEnabled(true);
                 b6.setEnabled(true);
                 b7.setEnabled(true);
                 b8.setEnabled(true);
            }
        
        
       
  
        }
        catch(Exception e)
        {
            System.out.println(e);
            //t1.setText(""+e);
        }
    }

    private void select() {
        try{
            
           query=t1.getText();
           res=stmt.executeQuery(query);
           
            while(res.next())
    {
        t2.append("\n"+res.getInt(1)+"\t"+res.getDate(2)+"\t"+res.getFloat(3)+"\t"+res.getString(4)+"\t"+res.getString(5));
    }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void insert() 
    {
        try{
            
           query=t1.getText();
           int newrow=stmt.executeUpdate(query);
           
            if(newrow>0)
    {
        JOptionPane.showMessageDialog(this,"Record Inserted successfully..."); 
    }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void delete() 
    {

	try
	{
    query=t1.getText();
    int row=stmt.executeUpdate(query);
           
            if(row>0)
    {
        JOptionPane.showMessageDialog(this,"Record Deleted successfully..."); 
    }
	}
	catch(Exception e)
	{
    	e.printStackTrace();
    	System.out.print(e);
	}
    }

    private void update()
     {
    	 try
    	 {
    		query=t1.getText();
    		int row=stmt.executeUpdate(query);
           
            if(row>0)
    	{
        	JOptionPane.showMessageDialog(this,"Record Updated successfully..."); 
    	}
	}
	catch(Exception e)
	{
    e.printStackTrace();
    System.out.print(e);
	}   
    }

    private void clearquery()
     {
       t1.setText("");
       
    }

    private void clearres()
     {
        t2.setText("");
        
      }

    private void disconnect() 
    {
        try
        {
            cn.close();
            JOptionPane.showMessageDialog(this,"Disconnection successfully...");
                 b1.setEnabled(true);
                 b2.setEnabled(false);
                 b3.setEnabled(false);
                 b4.setEnabled(false);
                 b5.setEnabled(false);
                 b6.setEnabled(false);
                 b6.setEnabled(false);
                 b7.setEnabled(false);
                 b8.setEnabled(false);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

    
}

		
