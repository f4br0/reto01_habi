import { type UserLogin } from "../type.d";

export const login = async (userLogin : UserLogin) => {

  return await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      ...userLogin
    })
  }).then(async res => {
    if(!res.ok) throw new Error('error en la peticion')
    return await res.json()
  }).then(res => {
    console.log("invoke login", res)
    return res})
  ;
}