
export const register = async (user: UserRegister) => {

  return await fetch('http://localhost:8080/auth/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      ...user
    })
  }).then(async res => {
    if(!res.ok) throw new Error('error en la peticion')
    return await res.json()
  }).then(res => {
    console.log("invoke register", res)
    return res})
  ;
}