package com.marcelomartins.atena.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.marcelomartins.atena.domain.PullRequest;

public class PullTableModel  extends AbstractTableModel{

	private List<PullRequest> lstPulls = new ArrayList<PullRequest>();
	private String[] columns = {"Number","Commits", "Changed Files", "Lifetime Type",
								"Lifetime", "Merged by", "Merged", "Comments"};
	
	public PullTableModel() {}
	
	public PullTableModel(List<PullRequest> lstPulls) {
		this.lstPulls = lstPulls;
	}
	
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lstPulls.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return lstPulls.get(rowIndex).getNumber();
		case 1:
			return lstPulls.get(rowIndex).getCommits();
		case 2:
			return lstPulls.get(rowIndex).getChanged_files();
		case 3:
			return lstPulls.get(rowIndex).getChanged_lines();
		case 4:
			return lstPulls.get(rowIndex).getLifetimeType();
		case 5:
			return lstPulls.get(rowIndex).getLifetime();
		case 6:
			return lstPulls.get(rowIndex).getMerged_by();
		case 7:
			return lstPulls.get(rowIndex).isMerged();
		case 8:
			return lstPulls.get(rowIndex).getComments();
		}
		
		return null;
	}
}
