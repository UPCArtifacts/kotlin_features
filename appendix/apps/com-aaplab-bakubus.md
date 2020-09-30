## com-aaplab-bakubus
----
#### Metrics provided by Detekt
* Number of lines of code 361
* Number of Kotlin files: 3
* Cyclomatic complexity: 41
* Cyclomatic complexity by thousands of lines: 207 

----
**7** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#range_expr">Range Expression</a> 
*	<a href="#property_delegation">Property Delegation</a> 
*	<a href="#destructuring_declaration">Destructuring Declaration</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.002336x%5E3%20&plus;-0.095682x%5E2%20&plus;%201.214675x%20&plus;%2030.425593',))
    * **R_Squared:** 0.81176656
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-7.6e-05x%5E4%20&plus;%200.005532x%5E3%20&plus;-0.13957x%5E2%20&plus;%201.431558x%20&plus;%2030.148349)
    * **R_Squared:** 0.81427888
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.280061%5Clog_%7B18.746859%7D%28x%29%20&plus;%2032.231164)
    * **R_Squared:** 0.63371572
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.117293x%20&plus;%2033.368421)
    * **R_Squared:** 0.36890614

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T11_3](../plots/com-aaplab-bakubus_type_inference_T11_3.png)
![com-aaplab-bakubus T11_4](../plots/com-aaplab-bakubus_type_inference_T11_4.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_type_inference_T6.png)
![com-aaplab-bakubus T1](../plots/com-aaplab-bakubus_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.223308x%20&plus;%2023.205263)
    * **R_Squared:** 0.8513807
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.964605%5Clog_%7B3.36596%7D%28x%29%20&plus;%2022.123623)
    * **R_Squared:** 0.84418709

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T1](../plots/com-aaplab-bakubus_lambda_T1.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.001019x%5E4%20&plus;%200.021977x%5E3%20&plus;0.17685x%5E2%20&plus;%20-4.467995x%20&plus;%2023.901186)
    * **R_Squared:** 0.85396082
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.020826x%5E3%20&plus;0.764589x%5E2%20&plus;%20-7.372479x%20&plus;%2027.614035',))
    * **R_Squared:** 0.83839789
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?3.320351x%5E%7B1.170329%7D%20&plus;%209.468532)
    * **R_Squared:** 0.40654433
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.553383x%20&plus;%208.189474)
    * **R_Squared:** 0.28362829
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.526108%5Clog_%7B3.515697%7D%28x%29%20&plus;%209.746854)
    * **R_Squared:** 0.07056221

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T11_4](../plots/com-aaplab-bakubus_safe_call_T11_4.png)
![com-aaplab-bakubus T11_3](../plots/com-aaplab-bakubus_safe_call_T11_3.png)
![com-aaplab-bakubus T4](../plots/com-aaplab-bakubus_safe_call_T4.png)
![com-aaplab-bakubus T1](../plots/com-aaplab-bakubus_safe_call_T1.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_safe_call_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000473x%5E4%20&plus;%20-0.023507x%5E3%20&plus;0.397508x%5E2%20&plus;%20-2.523375x%20&plus;%205.947885)
    * **R_Squared:** 0.58188006
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.003645x%5E3%20&plus;0.124773x%5E2%20&plus;%20-1.175578x%20&plus;%204.224974',))
    * **R_Squared:** 0.44632105
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?22.827704x%5E%7B1.166678%7D%20&plus;%201.534047)
    * **R_Squared:** 0.0360629
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.021805x%20&plus;%201.521053)
    * **R_Squared:** 0.01781214
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2063.667815%7D%28x%29%20&plus;%201.75)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T11_4](../plots/com-aaplab-bakubus_unsafe_call_T11_4.png)
![com-aaplab-bakubus T11_3](../plots/com-aaplab-bakubus_unsafe_call_T11_3.png)
![com-aaplab-bakubus T4](../plots/com-aaplab-bakubus_unsafe_call_T4.png)
![com-aaplab-bakubus T1](../plots/com-aaplab-bakubus_unsafe_call_T1.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_unsafe_call_T6.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.002531x%5E3%20&plus;0.100062x%5E2%20&plus;%20-1.228234x%20&plus;%206.568421',))
    * **R_Squared:** 0.8229566
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000137x%5E4%20&plus;%20-0.008265x%5E3%20&plus;0.178795x%5E2%20&plus;%20-1.617315x%20&plus;%207.065789)
    * **R_Squared:** 0.83169386
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?4.199735x%5E%7B0.664644%7D%20&plus;%201.899181)
    * **R_Squared:** 0.80152106
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.115038x%20&plus;%203.657895)
    * **R_Squared:** 0.38345865
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B46.135323%7D%28x%29%20&plus;%202.450001)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T11_3](../plots/com-aaplab-bakubus_range_expr_T11_3.png)
![com-aaplab-bakubus T11_4](../plots/com-aaplab-bakubus_range_expr_T11_4.png)
![com-aaplab-bakubus T5](../plots/com-aaplab-bakubus_range_expr_T5.png)
![com-aaplab-bakubus T2](../plots/com-aaplab-bakubus_range_expr_T2.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_range_expr_T6.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-45.385449%28x%20-3.49686%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000844x%5E3%20&plus;-0.033354x%5E2%20&plus;%200.409411x%20&plus;%200.477193',))
    * **R_Squared:** 0.8229566
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.001021%5Clog_%7B15.732947%7D%28x%29%20&plus;%201.081086)
    * **R_Squared:** 0.64936939
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.038346x%20&plus;%201.447368)
    * **R_Squared:** 0.38345865

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T9](../plots/com-aaplab-bakubus_property_delegation_T9.png)
![com-aaplab-bakubus T11_3](../plots/com-aaplab-bakubus_property_delegation_T11_3.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_property_delegation_T6.png)
![com-aaplab-bakubus T1](../plots/com-aaplab-bakubus_property_delegation_T1.png)
### <a name="destructuring_declaration">Destructuring Declaration</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000844x%5E3%20&plus;0.033354x%5E2%20&plus;%20-0.409411x%20&plus;%201.522807',))
    * **R_Squared:** 0.8229566
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?1.510377x%5E%7B0.664639%7D%20&plus;%20-0.033604)
    * **R_Squared:** 0.80152106
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.038346x%20&plus;%200.552632)
    * **R_Squared:** 0.38345865
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B1149.363932%7D%28x%29%20&plus;%200.15)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-aaplab-bakubus T11_3](../plots/com-aaplab-bakubus_destructuring_declaration_T11_3.png)
![com-aaplab-bakubus T5](../plots/com-aaplab-bakubus_destructuring_declaration_T5.png)
![com-aaplab-bakubus T2](../plots/com-aaplab-bakubus_destructuring_declaration_T2.png)
![com-aaplab-bakubus T6](../plots/com-aaplab-bakubus_destructuring_declaration_T6.png)
