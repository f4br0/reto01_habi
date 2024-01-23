export interface UserInfo {
  email?: string;
  token?: string;
  name?: string;
}

export interface UserLogin {
  email: string;
  password: string;
}

export interface UserRegister {
  email: string;
  password: string;
  name: string;
}

export interface Task {
  id?: number;
  state?: boolean;
  title: string;
  toUser: string;
  fromUser?: string;
  completedAt?: date;
}

export interface TaskResult {
  tasks: Task[];
  totalItems: number;
}