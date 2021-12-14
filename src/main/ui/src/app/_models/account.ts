export class Account { 
	id: number;
	login: string;
	password: string; 
	role: string;
	
	constructor(id: number, login: string, password: string, role: string) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}

}