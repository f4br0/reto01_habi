import { Button, Grid } from "@mui/material";
import { useMutation, useQueryClient } from "react-query";
import { Navigate } from "react-router-dom";
import { useUser } from "../hooks/useUser";
import DataTable from "../components/DataTable";

const Home = () => {

  const queryClient = useQueryClient()
  const { userInfo } = useUser();
  const { mutate } = useMutation({
    mutationFn: async () => window.localStorage.removeItem('loggedHabiUser'),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['user'] })
    }
  })

  console.log("user home", userInfo)

  return (<div>
    {userInfo === undefined && <Navigate to="/login" replace={true} />}
    HOME
    <Grid container justifyContent={"flex-end"}>

      <Button onClick={() => {
        mutate()
      }} >
        {`${userInfo?.name} (${userInfo?.email}) Logout`}           
      </Button>


    </Grid >
    <DataTable></DataTable>
  </div >)
};

export default Home;