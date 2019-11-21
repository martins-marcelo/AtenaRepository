package com.marcelomartins.atena.controllers;

import java.io.IOException;
import java.util.List;

import com.marcelomartins.atena.domain.PullRequest;
import com.marcelomartins.atena.domain.repository.PullRequestRepository;
import com.marcelomartins.atena.views.PanelSearch;
import com.marcelomartins.atena.views.PanelSearchResults;

public class SearchController {
	
	private PanelSearch panelSearch;
	
	private PanelSearchResults panelResults;
	
	private PullRequestRepository pullRepo;
	
	public SearchController(PanelSearch ps) {
		this.panelSearch = ps;
	}
	
	public void searchPulls(String link){
		pullRepo = new PullRequestRepository();
		List<PullRequest> lstPulls = null;
		
		try {
			lstPulls = pullRepo.getPullsList(link);
		} catch (IOException e) {
			panelSearch.showErrorMessage(true);
			return;
		}
		
		panelSearch.dispose();
		panelResults = new PanelSearchResults(link, lstPulls);
		panelResults.setVisible(true);
	}
}
