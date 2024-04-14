github.dismiss_out_of_range_messages

warn("A pull request must have some assignees") if github.pr_json["assignee"].nil?

# Android Lint
android_lint.gradle_task = "app:lint"
android_lint.filtering = true
Dir.glob("**/lint-results*.xml") do |xml|
    android_lint.report_file = xml
    android_lint.lint(inline_mode: true)
end

# Checkstyle format
checkstyle_format.base_path = Dir.pwd
Dir.glob("**/detekt.xml") do |file|
  checkstyle_format.report file
end

if status_report[:warning].empty?
    markdown("This PR is clean!")
end