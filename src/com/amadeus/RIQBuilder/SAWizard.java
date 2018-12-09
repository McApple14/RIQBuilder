package com.amadeus.RIQBuilder;

import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SAWizard extends Shell {

	KG175D kg;
	private static final Pattern PATTERN_IP = Pattern.compile(
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	private static final Pattern PATTERN_HOST = Pattern.compile(
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\/([0-9]|[0-2][0-9]|3[012])$");
	private Text textName;
	private Text textHost;
	private Text textPT;
	private Text textCT;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			KG175D kg = (new RIQBuilder(true)).getRIQs().get(0).getKGs()[KG175D.LEFT];
			SAWizard shell = new SAWizard(display, kg);
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

	/**
	 * Create the shell.
	 * @param display
	 */
	public SAWizard(Display display, KG175D kg) {
		super(display, SWT.SHELL_TRIM);
		this.kg = kg;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);
		
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name");
		
		textName = new Text(composite, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblHostcidr = new Label(composite, SWT.NONE);
		lblHostcidr.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHostcidr.setText("Host Address/CIDR");
		
		textHost = new Text(composite, SWT.BORDER);
		textHost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRemoteEcuPt = new Label(composite, SWT.NONE);
		lblRemoteEcuPt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRemoteEcuPt.setText("Remote ECU PT Address");
		
		textPT = new Text(composite, SWT.BORDER);
		textPT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRemoteEcuCt = new Label(composite, SWT.NONE);
		lblRemoteEcuCt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRemoteEcuCt.setText("Remote ECU CT Address");
		
		textCT = new Text(composite, SWT.BORDER);
		textCT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAddSa = new Button(this, SWT.NONE);
		btnAddSa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String host = PATTERN_HOST.matcher(textHost.getText()).matches() ? textHost.getText() : null;
				String pt = PATTERN_IP.matcher(textPT.getText()).matches() ? textPT.getText() : null;
				String ct = PATTERN_IP.matcher(textCT.getText()).matches() ? textCT.getText() : null;
				
				if(host == null) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Host input error. Correct host format: 192.168.1.1/32");
					dialog.open();
					return;
				}
				if(pt == null || ct == null) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("PT/CT Address input error. Correct PT/CT format: 192.168.1.1");
					dialog.open();
					return;
				}
				
				pt = textPT.getText().split("/")[0];
				
				kg.addSA(textName.getText(), textHost.getText().split("/")[0], textPT.getText(), textCT.getText(), Integer.parseInt(textHost.getText().split("/")[1]));
			}
		});
		GridData gd_btnAddSa = new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1);
		gd_btnAddSa.heightHint = 40;
		gd_btnAddSa.widthHint = 80;
		btnAddSa.setLayoutData(gd_btnAddSa);
		btnAddSa.setText("Add SA");
		
		Button btnClose = new Button(this, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().dispose();
			}
		});
		GridData gd_btnClose = new GridData(SWT.RIGHT, SWT.TOP, false, true, 1, 1);
		gd_btnClose.heightHint = 40;
		gd_btnClose.widthHint = 80;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Close");
	}
	
	private Shell getSelf() {
		return this;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
