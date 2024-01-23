import {
  Avatar,
  Box,
  Button,
  Container,
  CssBaseline,
  Grid,
  TextField,
  Typography,
} from "@mui/material";
import { LockOutlined } from "@mui/icons-material";
import { useState } from "react";
import { Link, Navigate, redirect } from "react-router-dom";
import { useMutation, useQuery, useQueryClient } from "react-query";
import { register } from "../services/register";
import { login } from "../services/login";
import { useUser } from "../hooks/useUser";



const Register = () => {

const [name, setName] = useState("");
const [email, setEmail] = useState("");
const [password, setPassword] = useState("");
const { userInfo } = useUser();

const queryClient = useQueryClient()

const { mutate, isLoading: isLoadingMutation } = useMutation({
  mutationFn: register,
  onSuccess: async (user: UserInfo) => {
    window.localStorage.setItem('loggedHabiUser',JSON.stringify(user)) 
    await queryClient.setQueryData(['user'], () => user)    
    
  }
})



const handleRegister = async () => {
  if (isLoadingMutation) return;
  const data: UserRegister = { email, password, name }
  mutate(data);
};
console.log("user regis",userInfo)
return (
  
  <>
  {userInfo && <Navigate to="/" replace={true} />}
    <Container maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          mt: 20,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "primary.light" }}>
          <LockOutlined />
        </Avatar>
        <Typography variant="h5">Register</Typography>
        <Box sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                name="name"
                required
                fullWidth
                id="name"
                label="Name"
                autoFocus
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Grid>
          </Grid>
          <Button
            disabled={isLoadingMutation}
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
            onClick={handleRegister}
          >
            Register
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link to="/login">Already have an account? Login</Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  </>
);
};

export default Register;