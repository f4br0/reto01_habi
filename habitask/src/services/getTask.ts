
export const getTask = async (page : number , items : number, token?: string) => {
  console.log(token)

  
  return await fetch(`http://localhost:8080/api/task/${page}/${items}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
      'Host': 'localhost:8081',
      'Accept': '*/*'
    },
  })
    .then(async res => {
      if (!res.ok) throw new Error('error en la peticion')
      return await res.json()
    }).then(res => {
      console.log("invoke get tasks", res)
      return res
    })
    ;
}