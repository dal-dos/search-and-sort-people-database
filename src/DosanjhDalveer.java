import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class DosanjhDalveer extends JFrame {
	private JComboBox customerBx;
	private JList dataLst;
	private JPanel contentPane;
	private JLabel lblCustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DosanjhDalveer frame = new DosanjhDalveer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}

	/**
	 * Create the frame.
	 */
	public DosanjhDalveer() {
		setUp();
		createEvents(); 
	}
	private void createEvents() {
		
		File myFile = new File("c://temp//as2data1275w20customer.csv");
		String arr[];
		try {
			Scanner myData = new Scanner(myFile);
			while(myData.hasNext()) {
				arr = myData.nextLine().split(",");
				customerBx.addItem(arr[1] + " " + arr[0]);
			}
			customerBx.setSelectedIndex(-1);
			myData.close();
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		customerBx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String current;
				String curArr[];
				String data;
				String ID;
				Boolean record = true;
				double total = 0;
				double cabinTotal = 0;
				double optionsTotal = 0;
				Boolean title = true;
				DefaultListModel dlm = new DefaultListModel();
				String fmt = "%-15s %10s %10s %20s %20s %20s %30s %20s %20s \n";
				NumberFormat numC = NumberFormat.getCurrencyInstance();
				current = (String)customerBx.getSelectedItem();
				curArr = current.split(" ");
				ID =curArr[curArr.length-1];
				File myFile = new File("c://temp//as2data1275w20.csv");
				try {
					Scanner myData = new Scanner(myFile);
					while(myData.hasNext()) {
						data = myData.nextLine();
						Cruise12022 customer= new Cruise12022(data);
						if(ID.equals(customer.getCID())) {
							record = false;
							if(title) {
								dlm.addElement(String.format(fmt,"Guests", "Start","Days", "Ship Name", "Cabin", "Cabin Amt", "Options", "Options Amt", "Total Amt"));
								title = false;
							}
							dlm.addElement(String.format(fmt, customer.getPersons(), customer.getStartDate(), customer.getDays(), customer.ShipName(), customer.Cabin(),numC.format(customer.CabinAmt()),customer.Options(),numC.format(customer.OptionsAmt()),numC.format(customer.TotalAmt())));
							total = total + customer.TotalAmt();
							cabinTotal = cabinTotal + customer.CabinAmt();
							optionsTotal = optionsTotal + customer.OptionsAmt();
						}
					}
					if(record)
						dlm.addElement("Sorry, no cruise record for this customer");
					else {
					dlm.addElement(String.format(fmt,"Total:", "","", "", "", numC.format(cabinTotal), "", numC.format(optionsTotal), numC.format(total)));
					}
					dlm.addElement("Program coded by DosanjhDalveer");
					myData.close();
					dataLst.setModel(dlm);
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		
		}

	private void setUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1279, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		customerBx = new JComboBox();

		lblCustomer = new JLabel("Customer");
		
		dataLst = new JList();
		dataLst.setFont(new Font("Courier New", Font.PLAIN, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblCustomer)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(customerBx, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(dataLst, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerBx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomer))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dataLst, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
