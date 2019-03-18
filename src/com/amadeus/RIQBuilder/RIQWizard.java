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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class RIQWizard extends Shell {

	private RIQBuilder builder;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQWizard shell = new RIQWizard(display, new RIQBuilder(true));
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
	private Composite leftKG;
	private Composite composite;
	private Text rightPTIP;
	private Text rightPTGW;
	private Text rightCTIP;
	private Text rightCTGW;
	private Composite compositeButtons;

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
		this.setSize(700, 300);
		this.setText("Add RIQ");
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		composite.setLayout(new GridLayout(2, false));
		
		lblNewLabel = new Label(composite, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 70;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Name:");
		
		textName = new Text(composite, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		compositeButtons = new Composite(this, SWT.NONE);
		compositeButtons.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 2));
		GridLayout gl_compositeButtons = new GridLayout(1, false);
		gl_compositeButtons.verticalSpacing = 10;
		gl_compositeButtons.horizontalSpacing = 10;
		gl_compositeButtons.marginHeight = 10;
		gl_compositeButtons.marginWidth = 10;
		compositeButtons.setLayout(gl_compositeButtons);
		new Label(compositeButtons, SWT.NONE);
		
		Button btnAdd = new Button(compositeButtons, SWT.NONE);
		GridData gd_btnAdd = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.heightHint = 40;
		gd_btnAdd.widthHint = 70;
		btnAdd.setLayoutData(gd_btnAdd);
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
		
		Button btnAutoCalc = new Button(compositeButtons, SWT.NONE);
		GridData gd_btnAutoCalc = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnAutoCalc.heightHint = 40;
		gd_btnAutoCalc.widthHint = 70;
		btnAutoCalc.setLayoutData(gd_btnAutoCalc);
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
		
		Button btnCancel = new Button(compositeButtons, SWT.NONE);
		GridData gd_btnCancel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel.heightHint = 40;
		gd_btnCancel.widthHint = 70;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setText("Cancel");
		
		leftKG = new Composite(this, SWT.NONE);
		leftKG.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_leftKG = new GridLayout(2, false);
		gl_leftKG.verticalSpacing = 10;
		gl_leftKG.horizontalSpacing = 10;
		gl_leftKG.marginHeight = 10;
		gl_leftKG.marginWidth = 10;
		leftKG.setLayout(gl_leftKG);
		
		lblLeftKgd = new Label(leftKG, SWT.NONE);
		lblLeftKgd.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblLeftKgd.setText("Left KG175D");
		
		lblPTIPLeft = new Label(leftKG, SWT.NONE);
		lblPTIPLeft.setText("PT IP: ");
		
		leftPTIP = new Text(leftKG, SWT.BORDER);
		leftPTIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblPTGWLeft = new Label(leftKG, SWT.NONE);
		lblPTGWLeft.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblPTGWLeft.setText("PT Gateway: ");
		
		leftPTGW = new Text(leftKG, SWT.BORDER);
		leftPTGW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblCTIPLeft = new Label(leftKG, SWT.NONE);
		lblCTIPLeft.setText("CT IP: ");
		
		leftCTIP = new Text(leftKG, SWT.BORDER);
		leftCTIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblCTGWLeft = new Label(leftKG, SWT.NONE);
		GridData gd_lblCTGWLeft = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblCTGWLeft.widthHint = 70;
		lblCTGWLeft.setLayoutData(gd_lblCTGWLeft);
		lblCTGWLeft.setText("CT Gateway: ");
		
		leftCTGW = new Text(leftKG, SWT.BORDER);
		leftCTGW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_label.widthHint = 10;
		label.setLayoutData(gd_label);
		
		Composite rightKG = new Composite(this, SWT.NONE);
		rightKG.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_rightKG = new GridLayout(2, false);
		gl_rightKG.verticalSpacing = 10;
		gl_rightKG.horizontalSpacing = 10;
		gl_rightKG.marginHeight = 10;
		gl_rightKG.marginWidth = 10;
		rightKG.setLayout(gl_rightKG);
		
		lblRightKgd = new Label(rightKG, SWT.NONE);
		lblRightKgd.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblRightKgd.setText("Right KG175D");
		
		Label label_1 = new Label(rightKG, SWT.NONE);
		label_1.setText("PT IP: ");
		
		rightPTIP = new Text(rightKG, SWT.BORDER);
		rightPTIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = new Label(rightKG, SWT.NONE);
		label_2.setText("PT Gateway: ");
		
		rightPTGW = new Text(rightKG, SWT.BORDER);
		rightPTGW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_3 = new Label(rightKG, SWT.NONE);
		label_3.setText("CT IP: ");
		
		rightCTIP = new Text(rightKG, SWT.BORDER);
		rightCTIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = new Label(rightKG, SWT.NONE);
		GridData gd_label_4 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_label_4.widthHint = 70;
		label_4.setLayoutData(gd_label_4);
		label_4.setText("CT Gateway: ");
		
		rightCTGW = new Text(rightKG, SWT.BORDER);
		rightCTGW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}
	
	private Shell getSelf() {return this;}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
