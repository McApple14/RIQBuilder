package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RIQWizard extends Shell {

	private RIQBuilder builder;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQWizard shell = new RIQWizard(display, new RIQBuilder());
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Label lblNewLabel;
	private Label lblLeftKgd;
	private Label lblRightKgd;
	private Label label;
	private Label lblPTIPLeft;
	private Text textName;
	private Text leftPTIP;
	private Text leftPTGW;
	private Label lblPTGWLeft;
	private Text leftCTIP;
	private Label lblCTIPLeft;
	private Text leftCTGW;

	/**
	 * Create the shell.
	 * @param display
	 */
	public RIQWizard(Display display, RIQBuilder builder) {
		super(display, SWT.SHELL_TRIM);
		this.builder = builder;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		this.setSize(700, 400);
		this.setText("Add RIQ");
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(25, 30, 55, 20);
		lblNewLabel.setText("Name:");
		
		lblLeftKgd = new Label(this, SWT.NONE);
		lblLeftKgd.setText("Left KG175D");
		lblLeftKgd.setBounds(25, 84, 101, 20);
		
		lblRightKgd = new Label(this, SWT.NONE);
		lblRightKgd.setText("Right KG175D");
		lblRightKgd.setBounds(289, 84, 101, 20);
		
		label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(259, 84, 2, 265);
		
		lblPTIPLeft = new Label(this, SWT.NONE);
		lblPTIPLeft.setBounds(25, 130, 80, 20);
		lblPTIPLeft.setText("PT IP: ");
		
		textName = new Text(this, SWT.BORDER);
		textName.setBounds(86, 30, 101, 21);
		
		leftPTIP = new Text(this, SWT.BORDER);
		leftPTIP.setBounds(130, 130, 101, 21);
		
		leftPTGW = new Text(this, SWT.BORDER);
		leftPTGW.setBounds(130, 180, 101, 21);
		
		lblPTGWLeft = new Label(this, SWT.NONE);
		lblPTGWLeft.setText("PT Gateway: ");
		lblPTGWLeft.setBounds(25, 180, 80, 20);
		
		leftCTIP = new Text(this, SWT.BORDER);
		leftCTIP.setBounds(130, 230, 101, 21);
		
		lblCTIPLeft = new Label(this, SWT.NONE);
		lblCTIPLeft.setText("CT IP: ");
		lblCTIPLeft.setBounds(25, 230, 80, 20);
		
		leftCTGW = new Text(this, SWT.BORDER);
		leftCTGW.setBounds(130, 280, 101, 21);
		
		Label lblCTGWLeft = new Label(this, SWT.NONE);
		lblCTGWLeft.setText("CT Gateway: ");
		lblCTGWLeft.setBounds(25, 280, 80, 20);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("PT IP: ");
		label_1.setBounds(289, 130, 80, 20);
		
		Text rightPTIP = new Text(this, SWT.BORDER);
		rightPTIP.setBounds(394, 130, 101, 21);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("PT Gateway: ");
		label_2.setBounds(289, 180, 80, 20);
		
		Text rightPTGW = new Text(this, SWT.BORDER);
		rightPTGW.setBounds(394, 180, 101, 21);
		
		Text rightCTIP = new Text(this, SWT.BORDER);
		rightCTIP.setBounds(394, 230, 101, 21);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("CT IP: ");
		label_3.setBounds(289, 230, 80, 20);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("CT Gateway: ");
		label_4.setBounds(289, 280, 80, 20);
		
		Text rightCTGW = new Text(this, SWT.BORDER);
		rightCTGW.setBounds(394, 280, 101, 21);
		
		Button btnAdd = new Button(this, SWT.NONE);
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
						MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
						dialog.setText("Input Error");
						dialog.setMessage("Invalid input; cannot have blank entries");
						dialog.open();
						return;
					}
				}
				builder.addRIQ(new RIQ(input[0],
						new KG175D(input[0].concat("LeftKG"),input[1],input[2],input[3],input[4]),
						new KG175D(input[0].concat("RightKG"),input[5],input[6],input[7],input[8])));
				builder.reInitialization(true);				
				System.out.println("RIQS: "+builder.getRIQs());
				getSelf().dispose();
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(544, 30, 130, 37);
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(544, 130, 130, 37);
		
		Button btnAutoCalc = new Button(this, SWT.NONE);
		btnAutoCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String name = textName.getText();
				if(name == "") {
					// Error dialog box
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
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
					temp.reInitialization(false);
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
		btnAutoCalc.setBounds(544, 80, 130, 	37);
	}
	
	private Shell getSelf() {return this;}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
