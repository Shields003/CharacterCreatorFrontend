import React from "react";
import { ReactDOM } from "react-dom";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Link,
  Redirect,
} from "react-router-dom";
import { useState, useEffect } from "react";
import {
  Table,
  TableContainer,
  TableHead,
  TableBody,
  TableRow,
  TextField,
  Button,
  Dialog,
  DialogContentText,
  DialogContent,
  DialogTitle,
  DialogActions,
  Grid,
  TableCell,
  Container,
  Typography,
} from "@mui/material";
import ButtonAppBar from "./ButtonAppBar";
import NavDrawer from "./NavDrawer";
import { Render } from "@testing-library/react";
import { Block, Navigation } from "@mui/icons-material";
//
import NewChar from "./CharacterPage/NewChar";
import HomePage from "./CharacterPage/HomePage";
import BlankCharacter from "./CharacterPage/BlankCharacter";
import SavedCharacters from "./CharacterPage/SavedCharacters";
import SignIn from "./CharacterPage/SignIn";
import "./App.css";
import get_character_choices from "./Functions/character_choices";
import GenChar from "./CharacterPage/GenChar";
import CharacterChoices from "./Components/CharacterChoices";
import { Box } from "@mui/system";

import dragon4 from "./dragon4.png";

import RedDragon from "./RedDragon.png";
import Image from "material-ui-image";

function App() {
  const [show_drawer, set_show_drawer] = useState(false);

  // console.log(character_data);
  return (
    <Router>
      <div>
        <Grid container>
          <Grid item xs={12}>
            <ButtonAppBar open_drawer={() => set_show_drawer(true)} />
          </Grid>
          <Grid item xs={12}>
            <Box
              sx={{
                display: "block",
                position: "fixed",
                width: "100%",
                zIndex: -5,
              }}
            >
              <img
                src={dragon4}
                style={{ margin: "0 auto", display: "block" }}
              />
            </Box>
            {/* <Box
              sx={{
                backgroundImage: `url(${skull})`,
                backgroundRepeat: "no-repeat",
                backgroundPosition: "center",
                minHeight: "663px",
              }}
            > */}
            <Routes>
              <Route path="/new_character" element={<NewChar />} />
              {/* <Route path="/new_character_blank" element={<BlankCharacter />} /> */}
              <Route path="/" element={<HomePage />} />
              <Route path="/GenChar" element={<CharacterChoices />} />
              <Route path="/blank_character" element={<BlankCharacter />} />
              <Route path="/SavedCharacters" element={<SavedCharacters />} />
              <Route path="/current_character" element={<new_character />} />
              <Route path="/SignIn" element={<SignIn />} />
              {/* <Route path="/contact" element={<Contact />} /> */}
            </Routes>
            {/* </Box> */}
          </Grid>
        </Grid>
        <NavDrawer open={show_drawer} onClose={() => set_show_drawer(false)} />
      </div>
    </Router>
  );
}

//store to db and send to new page
export default App;
