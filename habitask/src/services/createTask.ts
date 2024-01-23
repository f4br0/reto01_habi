import { Task } from "../type";

export const createTask = async (task: Task, token : string) => {
  console.log(' create task',token)
  return await fetch('http://localhost:8080/api/task', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
    body: JSON.stringify({
      ...task
    })
  }).then(async res => {
    if(!res.ok) throw new Error('error en la peticion')
    return await res.json()
  }).then(res => {
    console.log("invoke create task", res)
    return res})
  ;
}