<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/MartinSembrat/Mass_balance_calculator">
    <img src="src/main/resources/logo.png" alt="Logo" width="280" height="160">
  </a>

  <h3 align="center">Mass Balance Calculator API</h3>

  <p align="center">
    The application facilitates raw material flow management.
    <br />
    <a href="https://github.com/MartinSembrat/Mass_balance_calculator"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/MartinSembrat/Mass_balance_calculator">View Demo</a>
    ·
    <a href="https://github.com/MartinSembrat/Mass_balance_calculator/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    ·
    <a href="https://github.com/MartinSembrat/Mass_balance_calculator/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

## About The Project

![Product Name Screen Shot][product-screenshot]

The application is designed to calculate the amount of raw materials present in the finished products based on the recipes and weighments. 
Additionally, there is an option to load sales data, which helps to calculate the quantity of a particular raw material that was sold in finished products during a specific period of time. 
This feature helps maintain control over the mass balance of raw materials.

The tool was developed based on the existing data structure.

Index options:
* Upload File - load sales data in xlsx format (src/main/resources/Sales.xlsx)
* `Get All Sales` - displays loaded sales data
* `Get RM Content in FG` - displays products containing directly the raw material with the index entered in the Index field along with its content.
* `Get RM Content in FG Cakes` - displays products containing cake recipe with the raw material with the index entered in the Index field along with its content.
* `Get RM Content in FG Filling` - displays products containing fillings recipe with the raw material with the index entered in the Index field along with its content.
* `Get RM Content in product Overall` - displays products containing raw material anywhere with the index entered in the Index field along with its content.
* `Get Sale RM  in FG` - displays products containing raw material anywhere with the index entered in the Index field along with its content multiplied by the number of products sold at the selected time.


### Built With

* [![Spring-boot][Spring-boot]][Spring-boot-url]
* [![Maven][Maven]][Maven-url]
* [![H2database][H2database]][H2database-url]
* [![JS][JS]][JS-url]
* [![Java][java]][Java-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact

Martin Sembrat - [linkedin](https://www.linkedin.com/in/martin-sembrat/) - martinsembrat@gmail.com

Project Link: [https://github.com/MartinSembrat/Mass_balance_calculator](https://github.com/MartinSembrat/Mass_balance_calculator)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Chat GPT](https://chat.openai.com)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/MartinSembrat/Mass_balance_calculator.svg?style=for-the-badge
[contributors-url]: https://github.com/MartinSembrat/Mass_balance_calculator/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/MartinSembrat/Mass_balance_calculator.svg?style=for-the-badge
[forks-url]: https://github.com/MartinSembrat/Mass_balance_calculator/network/members
[stars-shield]: https://img.shields.io/github/stars/MartinSembrat/Mass_balance_calculator.svg?style=for-the-badge
[stars-url]: https://github.com/MartinSembrat/Mass_balance_calculator/stargazers
[issues-shield]: https://img.shields.io/github/issues/MartinSembrat/Mass_balance_calculator.svg?style=for-the-badge
[issues-url]: https://github.com/MartinSembrat/Mass_balance_calculator/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/martin-sembrat/
[product-screenshot]: src/main/resources/API_view.png
[Spring-boot]: https://img.shields.io/badge/Spring-boot-000000?style=for-the-badge&logo=spring&logoColor=white
[Spring-boot-url]: https://spring.io/
[Maven]: https://img.shields.io/badge/Maven-20232A?style=for-the-badge&logo=maven&logoColor=61DAFB
[Maven-url]: https://maven.apache.org/
[H2database]: https://img.shields.io/badge/H2database-35495E?style=for-the-badge&logo=h2databse&logoColor=4FC08D
[H2database-url]: https://www.h2database.com/
[JS]: https://img.shields.io/badge/JS-DD0031?style=for-the-badge&logo=javascript&logoColor=white
[JS-url]: https://js.org/
[Java]: https://img.shields.io/badge/Java-563D7C?style=for-the-badge&logo=oracle&logoColor=white
[Java-url]: https://www.java.com/
