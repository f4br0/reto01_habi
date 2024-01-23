import { useQuery, useQueryClient } from "react-query"
import { UserInfo } from "../type"

export const useUser = () => {
  const queryClient = useQueryClient()
  const { data: userInfo = undefined } = useQuery<UserInfo>(['user'], async () => {
    const user = localStorage.getItem('loggedHabiUser');
    if(user){
      await queryClient.invalidateQueries({ queryKey: ['tasks'] });
      return JSON.parse(user)
    }
    return undefined;
  },
    { refetchOnWindowFocus: false, staleTime: 1000 * 60 * 5 })
  return { userInfo }
}