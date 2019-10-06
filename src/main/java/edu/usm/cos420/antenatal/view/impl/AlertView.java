package edu.usm.cos420.antenatal.view.impl;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import edu.usm.cos420.antenatal.service.impl.ValidationService;

import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.service.impl.AlertService;

public class AlertView extends JFrame {
	/**
	 * The view that displays a list of patients that need a visit
	 */
	private static final long serialVersionUID = -5200310513229265843L;
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static JList<String> list;
	private static DefaultListModel<String> listModel;
	private static AlertService alertService = new AlertService();

	public AlertView() {
		createAlertView();
	}

	public static void createAlertView() {

		JFrame alertView = new JFrame("Patient Alerts");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(10);
		list.setToolTipText("Double click on a patient to send an email. "
				+ "If no patients are in the list try adding a patient!");
		list.setFixedCellWidth(250);

		/**
		 * 
		 * A mouselistener to listen when the user clicks on a patient name when a
		 * patient name is clicked on it brings up the dialog to enter the email address
		 * and if it is valid it sends an email to that address
		 */
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String emailAddress = "";
				int sizeOfList = list.getModel().getSize();
				if (e.getClickCount() == 2 && sizeOfList > 0) {
					int index = list.locationToIndex(e.getPoint());
					String patientName = listModel.getElementAt(index);
					while (!ValidationService.isEmailValid(emailAddress)) {
						emailAddress = JOptionPane.showInputDialog(alertView, "Enter email", "Email",
								JOptionPane.QUESTION_MESSAGE);
						if (emailAddress != null && ValidationService.isEmailValid(emailAddress)) {

							boolean emailWasSent = alertService.sendEmail(emailAddress, patientName);
							if (emailWasSent) {
								JOptionPane.showMessageDialog(alertView, "The email was sent!", "Success!",
										JOptionPane.INFORMATION_MESSAGE, null);
								break;
							} else {
								JOptionPane.showMessageDialog(alertView, "The email failed to send!", "Error",
										JOptionPane.ERROR_MESSAGE, null);
								break;
							}
						} else if (emailAddress == null) {
							break;
						} else {
							JOptionPane.showMessageDialog(alertView, "Invalid email address!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
						}
					}
				}
			}
		};
		list.addMouseListener(mouseListener);

		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setViewportView(list);
		panel.add(listScrollPane);
		JButton cancel = new JButton("Cancel");
		cancel.setToolTipText("Go back to the main screen");
		cancel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(cancel);
		alertView.setSize(WIDTH, HEIGHT);
		alertView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		alertView.setVisible(true);
		alertView.add(panel);
		alertView.setLocationRelativeTo(null);

		cancel.addActionListener((e) -> {
			alertView.dispose();
		});

		/*
		 * Run the patient alerts calculation on a separate thread so the main thread
		 * can be dedicated to handling the gui
		 */
		Thread patientAlerts = new Thread() {
			public void run() {
				for (String s : alertService.getPatientsNames())
					addPatient(s);
			}

		};
		patientAlerts.run();

	}

	/*
	 * Add a patient name to the listModel
	 */
	public static void addPatient(String name) {
		listModel.addElement(name);
	}
}
