import React from "react";
import PropTypes from "prop-types";
import { Grid } from "@mui/material";
import { border, Box, margin } from "@mui/system";
import { CenterFocusStrong, Home, Image } from "@mui/icons-material";

const HomePage = () => {
  return (
    <div>
      <Grid item xs={12}>
        <Box
          sx={{
            display: "block",
            position: "flex",
            zIndex: -5,
            textAlign: "center",
            border: "double",
            borderColor: "maroon",
            padding: "10px",
            margin: "8px",
          }}
        >
          <img
            src="http://img2.joyreactor.com/pics/post/full/art-CGI-dragon-3329461.jpeg"
            alt="Dragon"
            style={{ margin: "0 auto", display: "block" }}
            display="block"
            position="flex"
            min-width="600px"
            min-height="100%"
            width="100%"
            textAlign="center"
          ></img>
        </Box>
      </Grid>
    </div>
  );
};

HomePage.propTypes = {};

export default HomePage;
