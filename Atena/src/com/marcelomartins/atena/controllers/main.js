(function(){
	const search = document.getElementById("search");
	const profile = document.getElementById("profile");
	const url = "http://api.github.com/users";
	const client_id = "d95b5880e8a566cf210d";
	const client_secret = "cb66a2efc7c7e50e2921bc93c17dfdffdb5aa97b";
	const count = 7;
	const sort = "created: asc"

	async function getUser(user){
		const profileResponse = await fetch(`${url}/${user}?client_id=${client_id}&client_secret=${client_secret}`);

		const reposResponse = await fetch(`${url}/${user}/repos?per_page=${count}&sort=${sort}&client_id=${client_id}&client_secret=${client_secret}`);

		const profile = await profileResponse.json();
		const repos = await reposResponse.json();

		return {profile, repos};
	}

	function showRepos(repos){
		let output = '';

		repos.forEach(repo => {
			output += `
			<div class="card card-body mb-2">
				<div class="row">
					<div class="col-md-6"><a href="${repo.html_url}" target="_black">${repo.name}</a></div>
					<div class="col-md-6"><a href="${repo.owner.html_url}" target="_black">${repo.owner.login}</a></div> 
					<div class="col-md-6">
						<span class="badge badge-primary">Stars: ${repo.stargazers_count}</span>
						<span class="badge badge-primary">Watch: ${repo.watchers_count}</span>
						<span class="badge badge-primary">Forks: ${repo.forks_count}</span>
					</div>
				</div>
			</div>`
		});

		document.getElementById("repos").innerHTML = output;
	}

	function showProfile(user) {
		profile.innerHTML = `
		<div class="row mt-3">
		<div class="col-md-4">
			<div class="card" style="width:18rem;">
				<img class="card-img-top" src="${user.avatar_url}">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Repositorios: <span class="badge badge-success">${user.public_repos}</span></li>
					<li class="list-group-item">Seguidores: <span class="badge badge-primary">${user.followers}</span></li>
					<li class="list-group-item">Seguindo: <span class="badge badge-info">${user.following}</span></li>
				</ul>
				<div class="card-body">
					<a href="${user.html_url}" target="_blank" class="btn btn-warning btn-block">Ver perfil</a>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div id="repos"></div>
		</div>
	</div>`;
	}


	search.addEventListener("keyup", e =>{
		const user = e.target.value;
		if(user.length > 0){
			getUser(user).then(res => {
				showProfile(res.profile);
				showRepos(res.repos);
			});	
		}
		
	});
})();