import PropTypes from "prop-types";
import { Grid } from "@mui/material";
import { border, Box, color, margin } from "@mui/system";
import {
  CenterFocusStrong,
  Home,
  Image,
  PlayCircleFilledWhite,
} from "@mui/icons-material";
import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Paper } from "@mui/material";
import { Card } from "@mui/material";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
  return (
    <div>
      <Grid item xs={12}>
        <Box
          sx={{
            marginTop: "2rem",
            position: "fixed",
            border: "double",
            borderColor: "maroon",
            padding: "5px",
            margin: "10px",
            textalign: "center",
            // backgroundsize: "contain",
            // backgroundrepeat: "no-repeat",
            // backgroundposition: "center",
          }}
        >
          <img
            src="http://img2.joyreactor.com/pics/post/full/art-CGI-dragon-3329461.jpeg"
            alt="Dragon"
            style={{ margin: "0 auto", display: "flex" }}
            position="fixed"
            textalign="center"
            zindex="-5"
          ></img>
        </Box>
      </Grid>
    </div>
  );
};

HomePage.propTypes = {};

function SignUp(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      backgroundColor="rgba(255,255,255,.85)"
      zindex="5"
      {...props}
    >
      {"Copyright © "}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const theme = createTheme();

export default function SignIn() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    // eslint-disable-next-line no-console
    console.log({
      email: data.get("email"),
      password: data.get("password"),
    });
  };
  const navigate = useNavigate();
  return (
    <Grid sx={{ marginTop: "3.5rem" }}>
      <ThemeProvider theme={theme}>
        <Container
          marginTop="2rem"
          component="main"
          maxWidth="xs"
          backgroundColor="rgba(255,255,255,.85)"
        >
          <CssBaseline />
          <div>
            <Card
              sx={{
                zindex: 5,
                backgroundColor: "rgba(255,255,255,.95)",
                border: "ridge 8px",
              }}
              elevation={8}
            >
              <Box
                sx={{
                  marginTop: 1.5,
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                }}
              >
                <Box
                  sx={{
                    backgroundColor: "rgba(255,255,255,.85)",

                    marginTop: 5,
                    zindex: 10,
                  }}
                >
                  <Typography component="h2" variant="h5">
                    Sign in
                  </Typography>
                </Box>
                <Box
                  component="form"
                  onSubmit={handleSubmit}
                  noValidate
                  sx={{ mt: 1, marginTop: "10px" }}
                >
                  <TextField
                    sx={{
                      backgroundColor: "rgba(255,255,255,.85)",
                      border: "",
                    }}
                    margin="normal"
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    autoFocus
                  />
                  <Box sx={{}}>
                    <TextField
                      sx={{
                        backgroundColor: "rgba(255,255,255,.85)",
                        border: "",
                      }}
                      margin="normal"
                      required
                      fullWidth
                      name="password"
                      label="Password"
                      type="password"
                      id="password"
                      autoComplete="current-password"
                    />
                  </Box>
                  <FormControlLabel
                    sx={{
                      backgroundColor: "rgba(255,255,255,.95)",
                    }}
                    margin="normal"
                    required
                    fullWidth
                    control={<Checkbox value="remember" color="primary" />}
                    label="Remember me"
                  />
                  <Button type="submit" fullWidth variant="contained">
                    Sign In
                  </Button>
                  <Grid container>
                    <Grid item>
                      <Link href="#" variant="body2">
                        Forgot password?
                      </Link>
                    </Grid>
                    <Grid item>
                      <Link
                        sx={{ cursor: "pointer" }}
                        onClick={() => {
                          navigate("/SignUp");
                        }}
                        variant="body2"
                      >
                        {"Don't have an account? Sign Up"}
                      </Link>
                    </Grid>
                  </Grid>
                </Box>
              </Box>
              <SignUp sx={{ mt: 8, mb: 4 }} />
            </Card>
          </div>
        </Container>
      </ThemeProvider>
    </Grid>
  );
}
