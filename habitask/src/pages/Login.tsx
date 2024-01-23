import { LockOutlined } from "@mui/icons-material";
import {
  Container,
  CssBaseline,
  Box,
  Avatar,
  Typography,
  TextField,
  Button,
  Grid,
} from "@mui/material";
import { useState } from "react";
import { useMutation, useQueryClient } from "react-query";
import { Link, Navigate } from "react-router-dom";
import { login } from "../services/login";
import { useUser } from "../hooks/useUser";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const {userInfo} = useUser();
  
  const queryClient = useQueryClient()
  const { mutate } = useMutation({
    mutationFn: login,
    onSuccess: async (userRegistered: UserInfo) => {
      await queryClient.setQueryData(['user'], () => userRegistered)
      window.localStorage.setItem('loggedHabiUser',JSON.stringify(userRegistered))    
    }
  })

  const handleLogin = () => {
    const data: UserLogin = { email, password }
    mutate(data)
  };
  console.log("user login",userInfo)
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
          <Typography variant="h5">Login</Typography>
          <Box sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoFocus
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />

            <TextField
              margin="normal"
              required
              fullWidth
              id="password"
              name="password"
              label="Password"
              type="password"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
            />

            <Button
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleLogin}
            >
              Login
            </Button>
            <Grid container justifyContent={"flex-end"}>
              <Grid item>
                <Link to="/register">Don't have an account? Register</Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </>
  );
};

export default Login;