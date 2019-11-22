package com.marcelomartins.atena.domain;

public class PullRequest {
	
	/*
	 * Local identification number of pull request
	 */
	private Integer number;
	/*
	 * Number of commits associated to one pull request
	 */
	private Integer commits;
	/*
	 * Number of files changed
	 * This attribute is a sum of additions and deletions
	 */
	private Integer changed_files;
	/*
	 * Number of lines changed
	 * This attribute is a sum of additions and deletions
	 */
	private Integer changed_lines;	
	/*
	 * The type of lifetime
	 * >0-24 hours = very short
	 * >24-72 hours = short
	 * >72-240 hours = medium
	 * >240 hours = long
	 */
	private String lifetimeType;
	/*
	 * The life time in hours
	 */
	private int lifetime;
	/*
	 * This atrribute receives null as content if
	 * the state of pull request is not merged(closed)
	 */
	private String merged_by;
	/*
	 * Attribute that indicates if one pull request has been
	 * merged or not
	 */
	private boolean merged;
	/*
	 * Number of comments associate to one pull request
	 */
	private Integer comments;
	

	public Integer getNumber() {
		return number;
	}

	public Integer getCommits() {
		return commits;
	}

	public Integer getChanged_files() {
		return changed_files;
	}

	public Integer getChanged_lines() {
		return changed_lines;
	}

	public String getLifetimeType() {
		return lifetimeType;
	}
	
	public int getLifetime() {
		return lifetime;
	}

	public String getMerged_by() {
		return merged_by;
	}

	public boolean isMerged() {
		return merged;
	}

	public Integer getComments() {
		return comments;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setCommits(Integer commits) {
		this.commits = commits;
	}

	public void setChanged_files(Integer changed_files) {
		this.changed_files = changed_files;
	}

	public void setChanged_lines(Integer changed_lines) {
		this.changed_lines = changed_lines;
	}

	public void setLifetimeType(String lifetime) {
		this.lifetimeType = lifetime;
	}
	
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public void setMerged_by(String merged_by) {
		this.merged_by = merged_by;
	}

	public void setMerged(boolean merged) {
		this.merged = merged;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Pull "+number+" [commits=" + commits + ", changed_files=" + changed_files + ", changed_lines="
				+ changed_lines + ", lifetime=" + lifetime + ", lifetimeType="+lifetimeType+", merged_by=" + merged_by + ", merged=" + merged
				+ ", comments=" + comments + "]";
	}
	
	
	
}
