import * as React from "react";
import Grid from "@mui/material/Grid";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Checkbox from "@mui/material/Checkbox";
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper";
import Save from "@mui/icons-material/Save";

function not(a, b) {
  return a.filter((value) => b.indexOf(value) === -1);
}

function intersection(a, b) {
  return a.filter((value) => b.indexOf(value) !== -1);
}

export default function SpellTransfer() {
  const [checked, setChecked] = React.useState([]);
  const [save] = React.useState([]);
  const [left, setLeft] = React.useState([
    "Lighting Bolt",
    "Acid Splash",
    "Chill Touch",
    "Bless",
    "Detect Magic",
    "Cure Wounds",
    "Fog Cloud",
    "Bark Skin",
    "Sleep",
  ]);
  const [right, setRight] = React.useState([
    "Darkness",
    "Find Traps",
    "Invisibility",
    "Silence",
    "Fireball",
    "Haste",
    "Fear",
    "Charm Monster",
    "Ice Storm",
  ]);

  const leftChecked = intersection(checked, left);
  const rightChecked = intersection(checked, right);

  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };

  const handleAllRight = () => {
    setRight(right.concat(left));
    setLeft([]);
  };

  const handleCheckedRight = () => {
    setRight(right.concat(leftChecked));
    setLeft(not(left, leftChecked));
    setChecked(not(checked, leftChecked));
  };

  const handleCheckedLeft = () => {
    setLeft(left.concat(rightChecked));
    setRight(not(right, rightChecked));
    setChecked(not(checked, rightChecked));
  };

  const handleAllLeft = () => {
    setLeft(left.concat(right));
    setRight([]);
  };

  const customList = (items) => (
    <Paper
      elevation={8}
      sx={{
        width: 300,
        height: 600,
        overflow: "auto",
        backgroundColor: "rgba(255,255,255,.95)",
      }}
    >
      <List dense component="div" role="list">
        {items.map((value) => {
          const labelId = `transfer-list-item-${value}-label`;

          return (
            <ListItem
              key={value}
              role="listitem"
              button
              onClick={handleToggle(value)}
            >
              <ListItemIcon>
                <Checkbox
                  checked={checked.indexOf(value) !== -1}
                  tabIndex={-1}
                  disableRipple
                  inputProps={{
                    "aria-labelledby": labelId,
                  }}
                />
              </ListItemIcon>
              <ListItemText id={labelId} primary={` ${value}`} />
            </ListItem>
          );
        })}
        <ListItem />
      </List>
    </Paper>
  );

  return (
    <Grid
      container
      spacing={2}
      justifyContent="center"
      alignItems="center"
      marginTop="15px"
    >
      <Grid item>{customList(left)}</Grid>
      <Grid item>
        <Grid container direction="column" alignItems="center">
          <Button
            sx={{ my: 0.5, backgroundColor: "rgba(255,255,255,.95)" }}
            variant="outlined"
            size="small"
            onClick={handleAllRight}
            disabled={left.length === 0}
            aria-label="move all right"
          >
            ≫
          </Button>
          <Button
            sx={{ my: 0.5, backgroundColor: "rgba(255,255,255,.95)" }}
            variant="outlined"
            size="small"
            onClick={handleCheckedRight}
            disabled={leftChecked.length === 0}
            aria-label="move selected right"
          >
            &gt;
          </Button>
          <Button
            sx={{ my: 0.5, backgroundColor: "rgba(255,255,255,.95)" }}
            variant="outlined"
            size="small"
            onClick={handleCheckedLeft}
            disabled={rightChecked.length === 0}
            aria-label="move selected left"
          >
            &lt;
          </Button>
          <Button
            sx={{ my: 0.5, backgroundColor: "rgba(255,255,255,.95)" }}
            variant="outlined"
            size="small"
            onClick={handleAllLeft}
            disabled={right.length === 0}
            aria-label="move all left"
          >
            ≪
          </Button>
          <Button
            sx={{ my: 0.5, backgroundColor: "rgba(255,255,255,.95)" }}
            variant="outlined"
            size="small"
            onClick={save}
            //disabled={right.length === 0}
            aria-label="Save"
          >
            Save
          </Button>
        </Grid>
      </Grid>
      <Grid item>{customList(right)}</Grid>
    </Grid>
  );
}
