import * as React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Box from "@mui/material/Box";
import { Card, Container, Paper } from "@mui/material";
import IconButton from "@mui/material/IconButton";
import Input from "@mui/material/Input";
import FilledInput from "@mui/material/FilledInput";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import InputAdornment from "@mui/material/InputAdornment";
import FormHelperText from "@mui/material/FormHelperText";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import CharacterChoices from "../Components/CharacterChoices";
import get_character_choices from "../Functions/character_choices";
import useCharacter from "../hooks/useCharacter";
import { Grid } from "@mui/material";

import ComboBox from "../ComboBox";

export default function InputAdornments() {
  const [values, setValues] = React.useState({
    // amount: "",
    name: "",
    weight: "",
    level: "",
    race: "",
    _class: "",
    alignment: "",
    strength: "",
    dexterity: "",
    constitution: "",
    intelligence: "",
    wisdom: "",
    charisma: "",
    strBonus: "",
    liftBend: "",
    carryWeight: "",
    dexBonus: "",
    dodge: "",
    speed: "",
    conBonus: "",
    hitDice: "",
    saveThrow: "",
    intBonus: "",
    investBonus: "",
    profBonus: "",
    perceptBonus: "",
    insightBonus: "",
    wisBonus: "",
    charm: "",
    intimidate: "",
    chaBonus: "",
    eyeColor: "",
    hairColor: "",
    height: "",
    age: "",
  });

  const { character } = useCharacter();

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };
  console.log(character);

  return (
    <Container sx={{ paddingTop: "2rem" }} maxWidth={"md"}>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label=" - Character Name - "
              id="outlined-start-adornment"
              disabled
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>
        <Grid item xs={2}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Gesdfgnder-"
              // id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>
        <Grid item xs={2}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Level-"
              id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>

        <Grid item xs={2}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Exerience Points-"
              id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>

        <Grid item xs={3.5}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Race- "
              id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>

        <Grid item xs={3.5}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Class- "
              id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>
        <Grid item xs={3.5}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Alignment- "
              id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>
        <Grid item xs={1.5}>
          <Paper elevation={8}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
              label="-Age- "
              id="outlined-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>
        <Grid item xs={6}>
          <Paper
            elevation={8}
            sx={{ backgroundColor: "rgba(255,255,255,.95)", border: "double" }}
          >
            <Grid
              container
              spacing={2}
              sx={{
                margin: 0,
                width: "calc(100% - 16px)",
                paddingBottom: "16px",
              }}
            >
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  align-label="center"
                  label="Strength"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="center"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  label="Str Bonus"
                  sx={{ textAlign: "center" }}
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  label="Lift/Bend"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  label="Carry Weight"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Dexterity"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Dex Bonus"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Dodge"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Speed"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Constitution"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Con Bonus"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Hit Dice"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Saving Throw"
                  id="standard-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Intelligence"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Int Bonus"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Investigation"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Skill Bonus"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Wisdom"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Wis Bonus"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Perception"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Insight"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Charisma"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Cha Bonus"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Charm"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Intimidate"
                  id="standard-start-adornment"
                  fullWidth
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                  variant="standard"
                />
              </Grid>
            </Grid>
          </Paper>
        </Grid>

        <Grid item xs={6}>
          <Paper
            elevation={8}
            sx={{ backgroundColor: "rgba(255,255,255,.95)", border: "double" }}
          >
            <Grid
              container
              spacing={2}
              sx={{
                margin: 0,
                width: "calc(100% - 16px)",
                paddingBottom: "16px",
              }}
            >
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  label="Height"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  label="Weight"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Eye Color"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Hair Color"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>

              <Grid item xs={3}>
                <TextField
                  label="Total HP"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="Initiative +"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="AC"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={3}>
                <TextField
                  label="THACO"
                  id="outlined-start-adornment"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start"></InputAdornment>
                    ),
                  }}
                />
              </Grid>
            </Grid>
          </Paper>

          <Grid item xs={6} sx={{ paddingTop: "1rem" }} maxWidth={"md"}>
            <Paper
              elevation={8}
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
            >
              <TextField
                fullWidth
                sx={{ marginTop: "4px" }}
                label="-SKILLS- "
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
            </Paper>
          </Grid>
        </Grid>
        <Grid item xs={6}>
          <Card
            elevation={8}
            sx={{ backgroundColor: "rgba(255,255,255,.95)", border: "double" }}
          >
            <TextField
              sx={{ marginTop: "4px" }}
              fullWidth
              label="-Main Hand-"
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              sx={{ marginTop: "4px" }}
              fullWidth
              label="-Off Hand-"
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Card>
          <Grid item sx={{ paddingTop: "1rem" }}>
            <Paper
              elevation={8}
              sx={{
                backgroundColor: "rgba(255,255,255,.95)",
                border: "double",
              }}
            >
              <TextField
                fullWidth
                sx={{ marginTop: "8px" }}
                label="-EQUIPMENT- "
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
              <TextField
                fullWidth
                id="standard-start-adornment"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start"></InputAdornment>
                  ),
                }}
                variant="standard"
              />
            </Paper>
          </Grid>
        </Grid>
        <Grid
          item
          xs={6}
          sx={{ paddingTop: "1rem", paddingLeftt: "1rem" }}
          maxWidth={"md"}
        >
          <Paper
            elevation={8}
            sx={{ backgroundColor: "rgba(255,255,255,.95)", border: "double" }}
          >
            <TextField
              fullWidth
              sx={{ marginTop: "6px" }}
              label="-SPELLS- "
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
            <TextField
              fullWidth
              id="standard-start-adornment"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start"></InputAdornment>
                ),
              }}
              variant="standard"
            />
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
}
//some more changes
