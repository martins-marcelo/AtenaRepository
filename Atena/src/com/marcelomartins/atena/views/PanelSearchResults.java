package com.marcelomartins.atena.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.marcelomartins.atena.controllers.CsvController;
import com.marcelomartins.atena.domain.PullRequest;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelSearchResults extends JFrame{
	private static final long serialVersionUID = 1L;

	private List<PullRequest> lstPulls;

	private JPanel pCenter, pNorth;

	private JLabel lbTit, lbLink, lbExit;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private JButton btCSV;
	
	private String link;
	
	private CsvController csv;

	public PanelSearchResults(String link, List<PullRequest> lstPulls) {

		this.link = link;
		this.lstPulls = lstPulls;

		addPanel();
		
		System.out.println(lstPulls.size()+" resultados retornado do repositório: "+link);
		
		setTitle("Atena Search");
		setSize(850, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}	

	private void buildPanel() {

		pCenter = new JPanel();
		pCenter.setBounds(0, 45, 850, 430);
		pCenter.setBackground(Color.WHITE);
		
		pNorth = new JPanel();
		pNorth.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		pNorth.setBounds(0, 0, 850, 44);
		
		btCSV = new JButton("Generate CSV");
		btCSV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				csv = new CsvController();
				try {
					csv.writePulls(lstPulls);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btCSV.setFont(new Font("DejaVu Sans", Font.BOLD, 7));
		btCSV.setBounds(736, 56, 114, 90);
		pCenter.add(btCSV);
		
		addLabel();
		addTable();
	}

	private void addPanel() {
		buildPanel();
		getContentPane().setLayout(null);
		getContentPane().add(pCenter);
		
		JButton btDiscretize = new JButton("Mining Screen");
		btDiscretize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(
						null, "Go to mining?\nAll unsaved progress will be lost", "Mining Screen:",JOptionPane.YES_NO_OPTION);
				if(result == 0) {
					dispose();
					MiningScreen ms = new MiningScreen();
					ms.setVisible(true);
				}
			}
		});
		btDiscretize.setFont(new Font("DejaVu Sans", Font.BOLD, 7));
		btDiscretize.setBounds(736, 190, 114, 90);
		pCenter.add(btDiscretize);
		
		getContentPane().add(pNorth);
	}

	private void buildLabel() {
		lbTit = new JLabel("ATENA");
		lbTit.setForeground(Color.WHITE);
		lbTit.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lbTit.setHorizontalAlignment(SwingConstants.LEFT);
		lbTit.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbTit.setBounds(12, 12, 270, 32);

		lbLink = new JLabel(link);
		lbLink.setForeground(Color.WHITE);
		lbLink.setHorizontalAlignment(SwingConstants.CENTER);
		lbLink.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbLink.setBounds(165, 12, 520, 32);

		lbExit = new JLabel("Exit");
		lbExit.setForeground(Color.WHITE);
		lbExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VOLTAR PARA A JANELA ANTERIOR
				dispose();
				PanelSearch ps = new PanelSearch();
				ps.setVisible(true);
			}
		});
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbExit.setBounds(759, 12, 79, 32);
	}

	
	private void addLabel() {
		buildLabel();
		pCenter.setLayout(null);
		pNorth.setLayout(null);
		pNorth.add(lbTit);
		pNorth.add(lbLink);
		pNorth.add(lbExit);
	}
	

	private void buildTable() {
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model.addColumn("Number");
		model.addColumn("Commits");
		model.addColumn("Changed Files");
		model.addColumn("Changed Lines");
		model.addColumn("Lifetime Type");
		model.addColumn("Lifetime");
		model.addColumn("Merged by");
		model.addColumn("Merged");
		model.addColumn("Comments");
		
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		
		loadData();

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 22, 716, 385);
	}

	private void loadData() {
		model.setNumRows(0);

		for(PullRequest pull: lstPulls) 
			model.addRow(new Object[] {pull.getNumber(), pull.getCommits(),
					pull.getChanged_files(), pull.getChanged_lines(),
					pull.getLifetimeType(), pull.getLifetime(),
					pull.getMerged_by(), pull.isMerged(), pull.getComments()});
	}
	

	private void addTable() {
		buildTable();
		pCenter.add(scrollPane);
	}
	

	public static void main(String[] args) throws IOException {
		List<PullRequest> lista = new ArrayList<PullRequest>();
		PullRequest pull;

		for(int i=0; i<100; i++) {
			pull = new PullRequest();
			pull.setNumber(i);
			pull.setCommits(i+1);
			pull.setChanged_files(i+2);


			lista.add(pull);
		}
		
		PanelSearchResults psr = new PanelSearchResults("Teste", lista);
	}
}
