package com.amadeus.RIQBuilder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class TestingGround {
	
	private static RIQBuilder builder;
	
	protected Shell shell;
	private Text textName;
	private Text leftPTIP;
	private Text leftPTGW;
	private Text leftCTIP;
	private Text leftCTGW;
	private Text rightPTIP;
	private Text rightPTGW;
	private Text rightCTIP;
	private Text rightCTGW;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		builder = new RIQBuilder();
		try {
			TestingGround window = new TestingGround();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(700, 400);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(25, 30, 55, 15);
		lblNewLabel.setText("Name:");
		
		Label lblLeftKgd = new Label(shell, SWT.NONE);
		lblLeftKgd.setText("Left KG175D");
		lblLeftKgd.setBounds(25, 84, 80, 15);
		
		Label lblRightKgd = new Label(shell, SWT.NONE);
		lblRightKgd.setText("Right KG175D");
		lblRightKgd.setBounds(289, 84, 80, 15);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(259, 84, 2, 265);
		
		Label lblPTIPLeft = new Label(shell, SWT.NONE);
		lblPTIPLeft.setBounds(25, 130, 80, 15);
		lblPTIPLeft.setText("PT IP: ");
		
		textName = new Text(shell, SWT.BORDER);
		textName.setBounds(86, 30, 101, 21);
		
		leftPTIP = new Text(shell, SWT.BORDER);
		leftPTIP.setBounds(130, 130, 101, 21);
		
		leftPTGW = new Text(shell, SWT.BORDER);
		leftPTGW.setBounds(130, 180, 101, 21);
		
		Label lblPTGWLeft = new Label(shell, SWT.NONE);
		lblPTGWLeft.setText("PT Gateway: ");
		lblPTGWLeft.setBounds(25, 180, 80, 15);
		
		leftCTIP = new Text(shell, SWT.BORDER);
		leftCTIP.setBounds(130, 230, 101, 21);
		
		Label lblCTIPLeft = new Label(shell, SWT.NONE);
		lblCTIPLeft.setText("CT IP: ");
		lblCTIPLeft.setBounds(25, 230, 80, 15);
		
		leftCTGW = new Text(shell, SWT.BORDER);
		leftCTGW.setBounds(130, 280, 101, 21);
		
		Label lblCTGWLeft = new Label(shell, SWT.NONE);
		lblCTGWLeft.setText("CT Gateway: ");
		lblCTGWLeft.setBounds(25, 280, 80, 15);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("PT IP: ");
		label_1.setBounds(289, 130, 80, 15);
		
		rightPTIP = new Text(shell, SWT.BORDER);
		rightPTIP.setBounds(394, 130, 101, 21);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("PT Gateway: ");
		label_2.setBounds(289, 180, 80, 15);
		
		rightPTGW = new Text(shell, SWT.BORDER);
		rightPTGW.setBounds(394, 180, 101, 21);
		
		rightCTIP = new Text(shell, SWT.BORDER);
		rightCTIP.setBounds(394, 230, 101, 21);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("CT IP: ");
		label_3.setBounds(289, 230, 80, 15);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("CT Gateway: ");
		label_4.setBounds(289, 280, 80, 15);
		
		rightCTGW = new Text(shell, SWT.BORDER);
		rightCTGW.setBounds(394, 280, 101, 21);
		
		Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("RIQS: "+builder.getRIQs());
				String[] input = new String[] {
					textName.getText(), 	//Name
					leftPTIP.getText(), 	//Left PTIP
					leftPTGW.getText(),		//Left PTGW
					leftCTIP.getText(),		//Left CTIP
					leftCTGW.getText(),		//Left CTGW
					rightPTIP.getText(),	//Right PTIP
					rightPTGW.getText(),	//Right PTGW
					rightCTIP.getText(),	//Right CTIP
					rightCTGW.getText()		//Right CTGW
				};
				for(String in : input) {
					if (in=="" || in==null) {
						// Error dialog box
						MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						dialog.setText("Input Error");
						dialog.setMessage("Invalid input; cannot have blank entries");
						dialog.open();
						return;
					}
				}
				builder.addRIQ(new RIQ(input[0],
						new KG175D(input[0].concat("LeftKG"),input[1],input[2],input[3],input[4]),
						new KG175D(input[0].concat("RightKG"),input[5],input[6],input[7],input[8])));
				System.out.println("RIQS: "+builder.getRIQs());
				shell.dispose();
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(544, 30, 130, 37);
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(544, 130, 130, 37);
		
		Button btnAutoCalc = new Button(shell, SWT.NONE);
		btnAutoCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String name = textName.getText();
				if(name == "") {
					// Error dialog box
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Name required for Auto Calc");
					dialog.open();
				}
				else {
					System.out.println("Builder has "+builder.getRIQs().size()+" RIQS");
					RIQBuilder temp = new RIQBuilder(builder);
					System.out.println("TEMP has "+temp.getRIQs().size()+" RIQS");
					temp.addRIQ(new RIQ(name));
					System.out.println("TEMP has "+temp.getRIQs().size()+" RIQS");
					temp.reInitialization();
					System.out.println("TEMP has "+temp.getRIQs().size()+" RIQS");
					
					RIQ riq = temp.getRIQ(name);
					leftPTIP.setText(riq.getKGs()[0].getPTIP());
					leftPTGW.setText(riq.getKGs()[0].getPTGW());
					leftCTIP.setText(riq.getKGs()[0].getCTIP());
					leftCTGW.setText(riq.getKGs()[0].getCTGW());
					rightPTIP.setText(riq.getKGs()[1].getPTIP());
					rightPTGW.setText(riq.getKGs()[1].getPTGW());
					rightCTIP.setText(riq.getKGs()[1].getCTIP());
					rightCTGW.setText(riq.getKGs()[1].getCTGW());
				}
			}
		});
		btnAutoCalc.setText("Auto Calc");
		btnAutoCalc.setBounds(544, 80, 130, 37);
	}
}
