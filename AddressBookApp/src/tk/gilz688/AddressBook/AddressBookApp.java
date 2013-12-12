package tk.gilz688.AddressBook;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;

public class AddressBookApp {

	public static final String APP_NAME = "Address Book";
	private boolean notSaved;
	private JFrame frame;
	private AddressBookTableModel mModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JMenu mnSearch;
	private JMenu mnSort;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JMenuItem mntmPrint;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private File currentFile;
	private JCheckBoxMenuItem chckbxmntmSortByName;
	private JCheckBoxMenuItem chckbxmntmSortByZip;
	private JMenuItem mntmFindAgain;
	private String txtFind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {

		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressBookApp window = new AddressBookApp();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddressBookApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(APP_NAME);
		frame.setBounds(100, 100, 550, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panel,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 414,
												Short.MAX_VALUE)
										.addComponent(scrollPane,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 414,
												Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								237, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(7)));

		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performAddNewPerson();
			}
		});
		panel.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performEditPerson();
			}
		});
		panel.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performDeletePerson();
			}
		});
		panel.add(btnDelete);
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performNew();
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performOpen();
			}
		});
		mnFile.add(mntmOpen);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSave();
			}
		});
		mntmSave.setEnabled(false);
		mnFile.add(mntmSave);

		mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.setEnabled(false);
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSaveAs();
			}
		});
		mnFile.add(mntmSaveAs);

		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);

		mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performPrint();
			}
		});
		mntmPrint.setEnabled(false);
		mnFile.add(mntmPrint);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performQuit();
			}
		});
		mnFile.add(mntmQuit);

		mnSort = new JMenu("Sort");
		mnSort.setEnabled(false);
		menuBar.add(mnSort);

		chckbxmntmSortByName = new JCheckBoxMenuItem("Sort by Name");
		chckbxmntmSortByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCheckBoxMenuItem chckbxmntmSortByName = (JCheckBoxMenuItem) event
						.getSource();
				if (chckbxmntmSortByName != null) {
					if (chckbxmntmSortByName.isSelected())
						mModel.setSortingOrder(AddressBook.SortingOrder.NAME_ASCENDING);
				}
			}
		});
		mnSort.add(chckbxmntmSortByName);

		chckbxmntmSortByZip = new JCheckBoxMenuItem("Sort by ZIP");
		chckbxmntmSortByZip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCheckBoxMenuItem chckbxmntmSortByZip = (JCheckBoxMenuItem) event
						.getSource();
				if (chckbxmntmSortByZip != null) {
					if (chckbxmntmSortByZip.isSelected())
						mModel.setSortingOrder(AddressBook.SortingOrder.ZIPCODE_ASCENDING);
				}
			}
		});
		mnSort.add(chckbxmntmSortByZip);

		JCheckBoxMenuItemGroup sorting = new JCheckBoxMenuItemGroup();
		sorting.add(chckbxmntmSortByName);
		sorting.add(chckbxmntmSortByZip);

		mnSearch = new JMenu("Search");
		mnSearch.setEnabled(false);
		menuBar.add(mnSearch);

		JMenuItem mntmFind = new JMenuItem("Find");
		mntmFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performFind();
			}
		});
		mnSearch.add(mntmFind);

		mntmFindAgain = new JMenuItem("Find Again");
		mntmFindAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performFindAgain();
			}
		});
		mnSearch.add(mntmFindAgain);
		mntmFindAgain.setEnabled(false);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				performQuit();
			}
		});
	}

	/**
	 * Search for the specified text either just after the highlighted
	 * individual, or at the beginning of the address book if there if no
	 * highlighted individual.
	 */
	private void performFind() {
		FindDialog.createDialog(frame, new FindDialog.OKButtonClickListener() {

			@Override
			public void onOKButtonClick(String text) {
				int start = table.getSelectedRow() + 1;
				txtFind = text;
				if (start == -1)
					start = 0;
				int result = mModel.findNext(start, txtFind);
				if (result == -1) {
					// disable Find Again when no text is found
					mntmFindAgain.setEnabled(false);
					playSystemExclamation();
				} else {
					table.setRowSelectionInterval(result, result);
					mntmFindAgain.setEnabled(true);
				}
			}
		});
	}

	private void playSystemExclamation() {
		Toolkit.getDefaultToolkit().beep();
	}

	/**
	 * Search for the text specified for the most recent Find, just after the
	 * last individual found by the most recent Find or Find Again
	 */
	private void performFindAgain() {
		int start = table.getSelectedRow() + 1;
		if (start == -1)
			start = 0;
		int result = mModel.findNext(start, txtFind);
		if (result == -1) {
			// disable Find Again when no text is found
			mntmFindAgain.setEnabled(false);
			playSystemExclamation();
		} else {
			table.setRowSelectionInterval(result, result);
		}
	}

	/**
	 * Closes the application
	 */
	private void performQuit() {
		boolean notCancelled = offerToSaveChanges();
		if (notCancelled)
			frame.dispose();
	}

	/**
	 * Removes the currently selected Person
	 */
	private void performDeletePerson() {
		int result = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to delete this person?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		switch (result) {
		case JOptionPane.OK_OPTION:
			final int selectedIndex = table.getSelectedRow();
			mModel.remove(selectedIndex);
			notSaved = true;
			break;
		case JOptionPane.NO_OPTION:
		default:
			return;
		}
	}

	/**
	 * Shows the Add new Person dialog
	 */
	private void performAddNewPerson() {
		PersonDialog.createDialog(frame, null,
				new PersonDialog.OKButtonClickListener() {

					@Override
					public void onOKButtonClick(Person contact) {
						mModel.addElement(contact);
						notSaved = true;
					}
				});
	}

	/**
	 * Shows the Edit Person dialog
	 */
	private void performEditPerson() {
		final int selectedIndex = table.getSelectedRow();
		Person selectedPerson = mModel.getPersonAt(selectedIndex);
		PersonDialog.createDialog(frame, selectedPerson,
				new PersonDialog.OKButtonClickListener() {

					@Override
					public void onOKButtonClick(Person contact) {
						mModel.set(selectedIndex, contact);
						notSaved = true;
					}
				});
	}

	/**
	 * Shows the JFileChooser and saves the current Address Book to the selected
	 * File
	 * 
	 * @return <b>true</b> if file was successfully saved, <b>false</b>
	 *         otherwise
	 */
	private boolean performSaveAs() {
		JFileChooser saveChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Address Book XML file (*.abx)", "abx");
		saveChooser.setFileFilter(filter);
		saveChooser.setDialogTitle("Save As");
		int returnVal = saveChooser.showSaveDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = saveChooser.getSelectedFile();
			String name = selectedFile.getName();
			if (!name.endsWith(".abx"))
				selectedFile = new File(selectedFile.getParent(),
						name.concat(".abx"));
			saveAddressBook(selectedFile);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Saves the Address Book to the selected File
	 * 
	 * @return <b>true</b> if file was successfully saved, <b>false</b>
	 *         otherwise
	 */
	private boolean saveAddressBook(File selectedFile) {
		AddressBook addressBook = mModel.getAddressBook();

		try {
			AddressBookSerializer.write(addressBook, selectedFile);
			notSaved = false;
			mntmSave.setEnabled(false);
			frame.setTitle(APP_NAME + " (" + selectedFile.getPath() + ")");
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Error writing xml file", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	/**
	 * Shows the JFileChooser and opens an Address Book from the selected File
	 */
	private void performOpen() {
		boolean notCancelled = offerToSaveChanges();
		if (notCancelled) {
			JFileChooser openChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Address Book XML file (*.abx)", "abx");
			openChooser.setFileFilter(filter);
			int returnVal = openChooser.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = openChooser.getSelectedFile();
				AddressBook addressBook;
				try {
					addressBook = AddressBookSerializer.read(file);
					initializeList();
					for (Person contact : addressBook.getPersons()) {
						mModel.addElement(contact);
					}
					enableComponents(true);
					currentFile = file;
					frame.setTitle(APP_NAME + " (" + file.getPath() + ")");
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(frame, e.toString(),
									"Error reading xml file",
									JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Create a new Address Book
	 */
	private void performNew() {
		if (mModel != null) {
			boolean notCancelled = offerToSaveChanges();
			if (!notCancelled)
				return;
		}
		frame.setTitle(APP_NAME + " (Untitled)");
		initializeList();
		enableComponents(true);
		notSaved = false;
	}

	/**
	 * Save Address Book
	 * 
	 * @return <b>true</b> if file was successfully saved, <b>false</b>
	 *         otherwise
	 */
	private boolean performSave() {
		if (currentFile == null) {
			return performSaveAs();
		} else {
			return saveAddressBook(currentFile);
		}
	}

	/**
	 * Show print dialog
	 */
	private void performPrint() {
		PrinterJob job = PrinterJob.getPrinterJob();
		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException e) {
				JOptionPane.showMessageDialog(frame, e.toString(),
						"Error printing file", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Initialize a ListModel and use it as parameter to create a Contacts JList
	 */
	private void initializeList() {
		mModel = new AddressBookTableModel();
		table = new JTable(mModel);
		TableColumn avatarColumn = table.getColumnModel().getColumn(
				AddressBookTableModel.COLUMN_AVATAR);
		avatarColumn.setCellRenderer(new AvatarRenderer());
		table.getColumnModel().getColumn(AddressBookTableModel.COLUMN_AVATAR)
				.setMaxWidth(30);
		table.getColumnModel().getColumn(AddressBookTableModel.COLUMN_ZIPCODE)
				.setPreferredWidth(60);
		table.getColumnModel().getColumn(AddressBookTableModel.COLUMN_ADDRESS)
				.setPreferredWidth(200);
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (table == null)
					return;
				if (table.getSelectedRow() == -1)
					enableEditing(false);
				else
					enableEditing(true);
			}
		});
		scrollPane.setViewportView(table);

		// disable Find Again when a new Address is created or read from disk
		mntmFindAgain.setEnabled(false);

		// set default sorting order
		chckbxmntmSortByName.setSelected(true);
		mModel.setSortingOrder(AddressBook.SortingOrder.NAME_ASCENDING);
	}

	/**
	 * Enable components should only be available when a file is opened
	 */
	private void enableComponents(boolean enable) {
		mntmSave.setEnabled(enable);
		mntmSaveAs.setEnabled(enable);
		mntmPrint.setEnabled(enable);
		mnSearch.setEnabled(enable);
		mnSort.setEnabled(enable);
		btnAdd.setEnabled(enable);
	}

	/**
	 * Enable components should only be available when an item is selected
	 */
	private void enableEditing(boolean enable) {
		btnEdit.setEnabled(enable);
		btnDelete.setEnabled(enable);
	}

	/**
	 * Shows a confirmation dialog that offers the user to save changes
	 * 
	 * @return <b>true</b> if "Cancel" button was not pressed, <b>false</b>
	 *         otherwise
	 */
	private boolean offerToSaveChanges() {
		if (mModel != null && mModel.getRowCount() == 0)
			return true;

		if (notSaved) {
			String initialMessage;
			if (currentFile == null) {
				initialMessage = "File hasn't been saved yet. ";
			} else {
				initialMessage = "\'" + currentFile.getName()
						+ "\' has been modified. ";
			}
			int result = JOptionPane.showConfirmDialog(frame, initialMessage
					+ "Save changes first?", "Save Address Book",
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
			case JOptionPane.OK_OPTION:
				if (!performSave())
					return false;
				break;
			case JOptionPane.NO_OPTION:
				break;
			default:
				return false;
			}
		}
		return true;
	}
}
