package edu.usm.cos420.antenatal.view.impl;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import edu.usm.cos420.antenatal.domain.AntenatalPatient;

/**
 * The view that displays a list of visits for a given patient
 */
public class VisitHistoryView extends JFrame {


	private static final long serialVersionUID = -5200310513259265846L;
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static JList<String> list;
	private static DefaultListModel<String> listModel;
	private static AntenatalPatient thisPatient;

	public VisitHistoryView(AntenatalPatient patient) {
		thisPatient = patient;
		createVisitHistoryView();
	}

	public static void createVisitHistoryView() {

		JFrame visitHistoryView = new JFrame("Patient's Visit History");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));

		listModel = new DefaultListModel<String>();
		populateVisitList(thisPatient.getVisitHistory());
		
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(10);
		list.setToolTipText("Double click on a visit to view visit details");
		list.setFixedCellWidth(250);
		
		int sizeOfList = list.getModel().getSize();
		
		// check for empty list, inform user if no visits
		if (list.getModel().getSize() == 0) {
			JLabel emptyLabel = new JLabel("No visits on record");
			emptyLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(emptyLabel);
		}

		/**
		 * A mouselistener to listen when the user clicks on a visit
		 */
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && sizeOfList > 0) {
					int index = list.locationToIndex(e.getPoint());
					String visitString = listModel.getElementAt(index);
					
					int count = 0;
					while(count < visitString.length()) {
						if (visitString.charAt(count) == '-') {
				             break;
				         }
						count++;
					}
					
					Long visitID = Long.parseLong(visitString.substring(0, count-1));
					
					new VisitDetailsView(visitID);

				}
			}
		};
		list.addMouseListener(mouseListener);

		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setViewportView(list);
		panel.add(listScrollPane);

		JButton backButton = new JButton("Back");
		backButton.setToolTipText("Go back to the main screen");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(backButton);

		visitHistoryView.setSize(WIDTH, HEIGHT);
		visitHistoryView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		visitHistoryView.setVisible(true);
		visitHistoryView.add(panel);
		visitHistoryView.setLocationRelativeTo(null);

		backButton.addActionListener((e) -> {
			visitHistoryView.dispose();
		});
	}

	/*
	 * Populate the listModel with a string containing the visit ID and the date of the visit
	 */
	public static void populateVisitList(TreeMap<Long, LocalDate> visits) {
		String visitString = "";

		for(Map.Entry<Long,LocalDate> entry : visits.entrySet()) {
			Long visitID = entry.getKey();
			LocalDate visitDate = entry.getValue();
			visitString = visitID.toString() + " - " + visitDate.toString();
			listModel.addElement(visitString);
		}
	}
}
