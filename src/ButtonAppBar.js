import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import { useRecoilValue } from "recoil";
import characterAtom from "./atoms/character_atom";
const BACKEND = process.env.REACT_APP_BACKEND;

export default function ButtonAppBar(props) {
  const character = useRecoilValue(characterAtom);

  const test_button = async () => {
    try {
      console.log(character);
      const response = await fetch(`${BACKEND}/character/create`, {
        method: "POST",
        body: JSON.stringify(character),
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message);
      }
      const json_response = await response.json();
      console.log(json_response);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" color="primary">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={() => props.open_drawer()}
          >
            <MenuIcon />
          </IconButton>
          <Button
            variant="contained"
            color="secondary"
            onClick={test_button}
            sx={{ marginRight: "1rem" }}
          >
            Save Character
          </Button>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Mad Goat D&D Character Creator
          </Typography>
          <h3 color="inherit">Guest </h3>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
