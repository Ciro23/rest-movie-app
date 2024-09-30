export interface PersonForm {
  id?: number;
  name?: string;
  lastName?: string;
  gender?: "MALE" | "FEMALE";
  birth?: Date;
}
