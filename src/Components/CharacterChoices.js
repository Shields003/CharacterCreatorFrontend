import React from "react";
import { ReactDOM } from "react-dom";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Link,
  useLocation,
  useNavigate,
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
} from "@mui/material";

//

import get_character_choices from "../Functions/character_choices";
import CharSelect from "../CharacterPage/CharSelect";
import GenChar from "../CharacterPage/GenChar";
import useCharacter from "../hooks/useCharacter";
var superheroes = require("superheroes");

const mySuperHeroName = superheroes.random();

const store_char_to_db = (character_data) => {
  console.log(character_data);
};

const CharacterChoices = () => {
  const [character_data, set_character_data] = useState([]);
  const [character_name, set_character_name] = useState("");
  const [selected_character, set_selected_character] = useState(null);

  const { character, set_character } = useCharacter();

  // const get_random_user_data = async () => {
  //   try {
  //     const results = await fetch("https://www.dnd5eapi.co/api/monsters");
  //     const json_results = await results.json();
  //     const monsters = json_results.results;
  //     // console.log(monsters);
  //     for (let i = 0; i < monsters.length; i++) {
  //       console.log(monsters[i].url);
  //     }
  //   } catch (error) {
  //     console.log(error);
  //   }
  // };

  // useEffect(() => {
  //   console.log("Im inside my use Effect");
  //   get_random_user_data(5);
  // }, [character_name]);

  // console.log("IM outside my useEffect");

  const location = useLocation();
  const navigate = useNavigate();

  const handle_choices_click = () => {
    const choices = get_character_choices(5);
    set_character_data(choices);
    // const stats = get_character_stats();
    // set_character_data(stats);
  };
  return (
    <div>
      <Container sx={{ paddingTop: "1rem" }}>
        <Grid
          container
          spacing={2}
          justifyContent={"space-around"}
          alignItems="center"
        >
          <Grid item xs={4}>
            <TextField
              sx={{ width: "100%" }}
              value={mySuperHeroName}
              onChange={(event) => {
                set_character_name(event.target.value);
              }}
              helperText={
                Boolean(character_name.length === 0)
                  ? "Please enter a character Name"
                  : ""
              }
              label="Character Name"
              //error={Boolean(character_name.length === 0)}
            />
          </Grid>
          <Grid item xs={4}>
            <Button
              className="button"
              onClick={handle_choices_click}
              color="primary"
              variant="contained"
              //disabled={character_name.length === 0}
            >
              Get Character Choices
            </Button>
          </Grid>
          <Grid item xs={12} sx={{ backgroundColor: "rgba(255,255,255,.95)" }}>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    {/* <TableCell>Name</TableCell> */}
                    <TableCell>Select</TableCell>
                    <TableCell>Race</TableCell>
                    <TableCell>Class</TableCell>
                    <TableCell>Alignment</TableCell>
                    <TableCell align="center">Level</TableCell>
                    <TableCell align="center">Strength</TableCell>
                    <TableCell align="center">Dexterity</TableCell>
                    <TableCell align="center">Constitution</TableCell>
                    <TableCell align="center">Intelligence</TableCell>
                    <TableCell align="center">Wisdom</TableCell>
                    <TableCell align="center">Charisma</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {character_data.length !== 0 &&
                    // character_name.length !== 0 &&
                    character_data.map((character, i) => {
                      return (
                        <TableRow key={"Character-" + i}>
                          {/* <TableCell>{character_name}</TableCell> */}
                          <TableCell>
                            <Button
                              variant="contained"
                              color="primary"
                              onClick={() => set_selected_character(character)}
                            >
                              Choose
                            </Button>
                          </TableCell>
                          <TableCell>{character.race}</TableCell>
                          <TableCell>{character._class}</TableCell>
                          <TableCell align="center">
                            {character.alignment}
                          </TableCell>
                          <TableCell align="center">
                            {character.level}
                          </TableCell>

                          <TableCell align="center">
                            {character.strength}
                          </TableCell>
                          <TableCell align="center">
                            {character.dexterity}
                          </TableCell>
                          <TableCell align="center">
                            {character.constitution}
                          </TableCell>
                          <TableCell align="center">
                            {character.intelligence}
                          </TableCell>
                          <TableCell align="center">
                            {character.wisdom}
                          </TableCell>
                          <TableCell align="center">
                            {character.charisma}
                          </TableCell>
                        </TableRow>
                      );
                    })}
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
        </Grid>
      </Container>
      <Dialog
        open={Boolean(selected_character)}
        onClose={() => {
          set_selected_character(null);
        }}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Confirm Selected Character?"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Would you like to use the following Character?
          </DialogContentText>
          <CharSelect
            // selected_character={selected_character}
            // selected_name={character_name}
            combined_character={{
              ...selected_character,
              name: mySuperHeroName,
            }}
          />
          <GenChar
            // selected_character={selected_character}
            // selected_name={character_name}
            combined_character={{
              ...selected_character,
              name: mySuperHeroName,
            }}
          />
        </DialogContent>
        <DialogActions>
          <Button
            color="secondary"
            onClick={() => {
              set_selected_character(null);
            }}
          >
            Cancel
          </Button>

          <Button
            onClick={() => {
              //<Link to="./CharacterPage/NewChar.js" />;
              set_character({
                ...selected_character,
                name: mySuperHeroName,
              });
              navigate("/new_character");
              set_selected_character(null);
            }}
            color="primary"
            autoFocus
          >
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

CharacterChoices.propTypes = {};

export default CharacterChoices;
