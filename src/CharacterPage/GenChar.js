import React, { useState } from "react";
import PropTypes from "prop-types";
import { Grid, Typography, Box } from "@mui/material";
import { computeHeadingLevel } from "@testing-library/react";
import { boxSizing } from "@mui/system";

const GenChar = (props) => {
  const [items, setItems] = useState([]);
  const character = props.combined_character;
  console.table(character);
  const labels = {
    name: "Name:",
    _class: "Class:",
    strength: "Strength:",
    dexterity: "Dexterity:",
    constitution: "Constitution:",
    intelligence: "Intelligence:",
    wisdom: "Wisdom:",
    charisma: "Charisma:",
    race: "Race:",
    level: "Level:",
    alignment: "Alignment:",
    gender: "Gender:",
  };
  const keys = [
    "name",
    "race",
    "_class",
    "level",
    "alignment",
    "gender",
    "strength",
    "dexterity",
    "constitution",
    "intelligence",
    "wisdom",
    "charisma",
  ];

  return (
    <Box sx={{ maxWidth: "300px", boxSizing: "border-box", padding: "16px" }}>
      <Grid container spacing={1}>
        {keys.map((key, i) => {
          return (
            <Grid
              item
              container
              spacing={2}
              key={key}
              justifyContent="space-around"
            >
              <Grid item xs={6}>
                <Typography variant="body1" fontWeight="bold">
                  {labels[key]}
                </Typography>
              </Grid>
              <Grid item xs={3}>
                <Typography variant="body1">{character[key]}</Typography>
              </Grid>
            </Grid>
          );
        })}
      </Grid>
    </Box>
  );
};

GenChar.propTypes = {};

export default GenChar;
