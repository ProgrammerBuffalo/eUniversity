export class Student {
	id: number;
	fullName: string;
	accountId: number;

	constructor(id: number, fullName: string, accountId: number) {
		this.id = id;
		this.fullName = fullName;
		this.accountId = accountId;
	}
}