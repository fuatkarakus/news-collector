<template>
  <div>
    <b-navbar toggleable="lg" variant="faded" type="light">
      <b-container>
        <b-navbar-brand href="#">News Collector</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <!-- Right aligned nav items -->
          <b-navbar-nav>
            <b-nav-item :to="{ name: 'Home' }">Home</b-nav-item>
            <b-nav-item :to="{ name: 'About' }">About</b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-container>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      meal: ''
    };
  }
};
</script>
